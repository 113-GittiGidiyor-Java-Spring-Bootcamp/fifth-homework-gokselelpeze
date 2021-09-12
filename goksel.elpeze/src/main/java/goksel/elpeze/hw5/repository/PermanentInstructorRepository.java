package goksel.elpeze.hw5.repository;

import goksel.elpeze.hw5.model.Instructor;
import goksel.elpeze.hw5.model.PermanentInstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermanentInstructorRepository extends InstructorRepository {

    // get 3 instructors with the greatest salary
    List<PermanentInstructor> findFirst3PermanentInstructorsByOrderByFixedSalaryDesc();

    //get 3 instructors with the lowest salary
    List<PermanentInstructor> findFirst3PermanentInstructorsByOrderByFixedSalaryAsc();

}