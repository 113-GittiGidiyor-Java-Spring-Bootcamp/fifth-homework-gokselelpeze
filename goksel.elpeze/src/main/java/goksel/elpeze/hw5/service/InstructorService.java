package goksel.elpeze.hw5.service;

import goksel.elpeze.hw5.dto.InstructorDTO;
import goksel.elpeze.hw5.dto.PermanentInstructorDTO;
import goksel.elpeze.hw5.dto.VisitingResearcherDTO;
import goksel.elpeze.hw5.exceptions.InstructorIsAlreadyExistException;
import goksel.elpeze.hw5.mappers.InstructorMapper;
import goksel.elpeze.hw5.model.Instructor;
import goksel.elpeze.hw5.model.Logger;
import goksel.elpeze.hw5.model.PermanentInstructor;
import goksel.elpeze.hw5.model.VisitingResearcher;
import goksel.elpeze.hw5.repository.InstructorRepository;
import goksel.elpeze.hw5.repository.LoggerRepository;
import goksel.elpeze.hw5.repository.PermanentInstructorRepository;
import goksel.elpeze.hw5.repository.VisitingResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService {


    private final InstructorRepository instructorRepository;
    private final PermanentInstructorRepository permanentInstructorRepository;
    private final VisitingResearcherRepository visitingResearcherRepository;
    private final InstructorMapper instructorMapper;
    private final LoggerRepository loggerRepository;

    public boolean existsById(int instructorId) {
        return instructorRepository.existsById(instructorId);
    }

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public InstructorDTO findInstructorById(int instructorId) {
        Instructor foundInstructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException("Instructor with id: " + instructorId + " can not be found!"));

        if (foundInstructor.getClass() == PermanentInstructor.class) {
            return instructorMapper.mapFromPermanentInstructortoPermanentInstructorDTO((PermanentInstructor) foundInstructor);
        } else {
            return instructorMapper.mapFromVisitingResearchertoVisitingResearcherDTO((VisitingResearcher) foundInstructor);
        }
    }

    /**
     * Check instructor is already existing
     * If not throw exception
     * Else save to db
     *
     * @param permanentInstructorDTO
     * @return Optional<Instructor>
     */
    @Transactional
    public Optional<Instructor> savePermanentInstructor(PermanentInstructorDTO permanentInstructorDTO) {
        PermanentInstructor instructor = instructorMapper.mapFromPermanentInstructorDTOtoPermanentInstructor(permanentInstructorDTO);
        if (Objects.nonNull(instructorRepository.findInstructorByPhoneNumber(instructor.getPhoneNumber()))) {
            throw new InstructorIsAlreadyExistException("Instructor " + instructor.getName() + " is already exists!");
        }
        return Optional.of(permanentInstructorRepository.save(instructor));
    }

    /**
     * Check instructor is already existing
     * If not throw exception
     * Else save to db
     *
     * @param visitingResearcherDTO
     * @return Optional<Instructor>
     */
    @Transactional
    public Optional<Instructor> saveVisitingResearcher(VisitingResearcherDTO visitingResearcherDTO) {
        VisitingResearcher instructor = instructorMapper.mapFromVisitingResearcherDTOtoVisitingResearcher(visitingResearcherDTO);
        if (Objects.nonNull(instructorRepository.findInstructorByPhoneNumber(instructor.getPhoneNumber()))) {
            throw new InstructorIsAlreadyExistException("Instructor " + instructor.getName() + " is already exists!");
        }
        return Optional.of(visitingResearcherRepository.save(instructor));
    }

    @Transactional
    public void deletePermanentInstructor(PermanentInstructor instructor) {
        instructorRepository.delete(instructor);
    }

    @Transactional
    public void deleteVisitingResearcher(VisitingResearcher instructor) {
        instructorRepository.delete(instructor);
    }

    @Transactional
    public void deleteInstructorById(int instructorId) {
        instructorRepository.deleteById(instructorId);
    }

    @Transactional
    public List<Instructor> findInstructorsByName(String name) {
        return instructorRepository.findInstructorsByName(name);
    }

    @Transactional
    public void deleteInstructorByName(String name) {
        instructorRepository.deleteInstructorByName(name);
    }

    public Instructor getCourseInstructorById(int instructorId) {
        return instructorRepository.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException("Instructor with id: " + instructorId + " can not be found!"));
    }

    public void updateSalaryOfPermanentInstructor(int instructorId, String clientInfo, int percentage) {
        PermanentInstructor permanentInstructor = permanentInstructorRepository.getPermanentInstructorById(instructorId);
        double beforeSalary = permanentInstructor.getFixedSalary();
        permanentInstructorRepository.updateSalaryOfPermanentInstructor(instructorId, percentage);
        permanentInstructor = permanentInstructorRepository.getPermanentInstructorById(instructorId);
        double afterSalary = permanentInstructor.getFixedSalary();
        saveLogToDatabase(instructorId, clientInfo, beforeSalary, afterSalary, percentage);
    }

    private void saveLogToDatabase(int instructorId, String clientInfo, double beforeSalary, double afterSalary, double percentage) {
        Logger logger = Logger.builder().id(instructorId)
                .clientInfo(clientInfo)
                .beforeSalary(beforeSalary)
                .afterSalary(afterSalary)
                .percentage(percentage).
                requestTime(LocalDate.now()).build();
        loggerRepository.save(logger);
    }

    public void updateSalaryOfVisitingResearcher(int instructorId, String clientInfo, int percentage) {
        VisitingResearcher visitingResearcher = visitingResearcherRepository.getVisitingResearcherById(instructorId);
        double beforeSalary = visitingResearcher.getHourlySalary();
        visitingResearcherRepository.updateSalaryOfVisitingResearcher(instructorId, percentage);
        visitingResearcher = visitingResearcherRepository.getVisitingResearcherById(instructorId);
        double afterSalary = visitingResearcher.getHourlySalary();
        saveLogToDatabase(instructorId, clientInfo, beforeSalary, afterSalary, percentage);

    }
}
