package goksel.elpeze.hw5.controller;

import goksel.elpeze.hw5.dto.PermanentInstructorDTO;
import goksel.elpeze.hw5.dto.VisitingResearcherDTO;
import goksel.elpeze.hw5.model.Instructor;
import goksel.elpeze.hw5.model.Logger;
import goksel.elpeze.hw5.model.PermanentInstructor;
import goksel.elpeze.hw5.model.VisitingResearcher;
import goksel.elpeze.hw5.service.InstructorService;
import goksel.elpeze.hw5.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InstructorController {

    private final InstructorService instructorService;
    private final LoggerService loggerService;

    @Autowired
    public InstructorController(InstructorService instructorService, LoggerService loggerService) {
        this.instructorService = instructorService;
        this.loggerService = loggerService;
    }

    @PutMapping("/instructors/permanentInstructor/{instructorId}/{percentage}")
    public ResponseEntity<?> updateSalaryOfPermanentInstructor(@PathVariable int instructorId, @PathVariable int percentage) {
        if (instructorService.existsById(instructorId)) {
            String clientInfo = "test";
            instructorService.updateSalaryOfPermanentInstructor(instructorId, clientInfo, percentage);
            return new ResponseEntity<>("Instructor with id: " + instructorId + "'s salary is changed by " + percentage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Instructor with id: " + instructorId + " not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/instructors/visitingresearcher/{instructorId}/{percentage}")
    public ResponseEntity<?> updateSalaryOfVisitingResearcher(@PathVariable int instructorId, @PathVariable int percentage) {
        if (instructorService.existsById(instructorId)) {
            String clientInfo = "test";
            instructorService.updateSalaryOfVisitingResearcher(instructorId, clientInfo, percentage);
            return new ResponseEntity<>("Instructor with id: " + instructorId + "'s salary is changed by " + percentage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Instructor with id: " + instructorId + " not found.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/instructors/salaryId/{id}")
    public ResponseEntity<?> findSalaryLogById(@PathVariable int id) {
        List<Logger> result = loggerService.findSalaryLogById(id);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/instructors/salaryDate/{date}")
    public ResponseEntity<?> findSalaryLogByDate(@PathVariable String date) {
        List<Logger> result = loggerService.findSalaryLogByDate(date);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/instructors")
    public ResponseEntity<?> findAllInstructors() {
        List<Instructor> result = instructorService.findAllInstructors();

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/instructors/{instructorId}")
    public ResponseEntity<?> findInstructorById(@PathVariable int instructorId) {
        return new ResponseEntity<>(instructorService.findInstructorById(instructorId), HttpStatus.OK);
    }

    @PostMapping("/instructors/permanentInstructor")
    public ResponseEntity<?> savePermanentInstructor(@RequestBody @Valid PermanentInstructorDTO permanentInstructorDTO) {
        Optional<Instructor> resultOptional = instructorService.savePermanentInstructor(permanentInstructorDTO);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/instructors/visitingResearcher")
    public ResponseEntity<?> saveVisitingResearcher(@RequestBody @Valid VisitingResearcherDTO visitingResearcherDTO) {
        Optional<Instructor> resultOptional = instructorService.saveVisitingResearcher(visitingResearcherDTO);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/instructors/permanentInstructor")
    public ResponseEntity<?> updatePermanentInstructor(@RequestBody @Valid PermanentInstructorDTO permanentInstructorDTO) {
        if (instructorService.existsById(permanentInstructorDTO.getId())) {
            return new ResponseEntity<>(instructorService.savePermanentInstructor(permanentInstructorDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Instructor with id: " + permanentInstructorDTO.getId() + " not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/instructors/visitingResearcher")
    public ResponseEntity<?> updateVisitingResearcher(@RequestBody @Valid VisitingResearcherDTO visitingResearcherDTO) {
        if (instructorService.existsById(visitingResearcherDTO.getId())) {
            return new ResponseEntity<>(instructorService.saveVisitingResearcher(visitingResearcherDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Instructor with id: " + visitingResearcherDTO.getId() + " not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/instructors/permanentInstructor")
    public ResponseEntity<String> deletePermanentInstructor(@RequestBody @Valid PermanentInstructor instructor) {
        int instructorId = instructor.getId();
        if (instructorService.existsById(instructorId)) {
            instructorService.deletePermanentInstructor(instructor);
            return new ResponseEntity<>("Instructor with id: " + instructorId + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Instructor with id: " + instructorId + " not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/instructors/visitingResearcher")
    public ResponseEntity<String> deleteVisitingResearcher(@RequestBody @Valid VisitingResearcher instructor) {
        int instructorId = instructor.getId();
        if (instructorService.existsById(instructorId)) {
            instructorService.deleteVisitingResearcher(instructor);
            return new ResponseEntity<>("Instructor with id: " + instructorId + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Instructor with id: " + instructorId + " not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/instructors/{instructorId}")
    public ResponseEntity<String> deleteInstructorById(@PathVariable int instructorId) {
        if (instructorService.existsById(instructorId)) {
            instructorService.deleteInstructorById(instructorId);
            return new ResponseEntity<>("Instructor with id: " + instructorId + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Instructor with id: " + instructorId + " not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/instructors/byName")
    public ResponseEntity<?> findInstructorsByFirstNameAndLastName(@RequestParam String name) {
        return new ResponseEntity<>(instructorService.findInstructorsByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/instructors/byName")
    public ResponseEntity<String> deleteInstructorByFirstNameAndLastName(@RequestParam String name) {
        if (instructorService.findInstructorsByName(name).isEmpty()) {
            return new ResponseEntity<>("Instructor with name: " + name + " not found.", HttpStatus.BAD_REQUEST);
        } else {
            instructorService.deleteInstructorByName(name);
            return new ResponseEntity<>("Instructor with name: " + name + " deleted.", HttpStatus.OK);
        }
    }
}
