package goksel.elpeze.hw5.service;

import goksel.elpeze.hw5.dto.PermanentInstructorDTO;
import goksel.elpeze.hw5.dto.VisitingResearcherDTO;
import goksel.elpeze.hw5.exceptions.InstructorIsAlreadyExistException;
import goksel.elpeze.hw5.mappers.InstructorMapper;
import goksel.elpeze.hw5.model.Instructor;
import goksel.elpeze.hw5.model.PermanentInstructor;
import goksel.elpeze.hw5.model.VisitingResearcher;
import goksel.elpeze.hw5.repository.InstructorRepository;
import goksel.elpeze.hw5.repository.PermanentInstructorRepository;
import goksel.elpeze.hw5.repository.VisitingResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public boolean existsById(int instructorId) {
        return instructorRepository.existsById(instructorId);
    }

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> findInstructorById(int instructorId) {
        return instructorRepository.findById(instructorId);
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

    public List<PermanentInstructor> findFirst3PermanentInstructorsByOrderByFixedSalaryDesc() {
        return permanentInstructorRepository.findFirst3PermanentInstructorsByOrderByFixedSalaryDesc();
    }

    public List<PermanentInstructor> findFirst3PermanentInstructorsByOrderByFixedSalaryAsc() {
        return permanentInstructorRepository.findFirst3PermanentInstructorsByOrderByFixedSalaryAsc();
    }

    public List<VisitingResearcher> findFirst3VisitingResearchersByOrderByHourlySalaryDesc() {
        return visitingResearcherRepository.findFirst3VisitingResearchersByOrderByHourlySalaryDesc();
    }

    public List<VisitingResearcher> findFirst3VisitingResearchersByOrderByHourlySalaryAsc() {
        return visitingResearcherRepository.findFirst3VisitingResearchersByOrderByHourlySalaryAsc();
    }

}
