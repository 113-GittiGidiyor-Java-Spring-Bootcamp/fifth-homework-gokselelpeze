package goksel.elpeze.hw5.repository;

import goksel.elpeze.hw5.model.PermanentInstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermanentInstructorRepository extends InstructorRepository {


    @Query("UPDATE PermanentInstructor SET fixedSalary = fixedSalary + (fixedSalary * ?2) WHERE id=?1")
    void updateSalaryOfPermanentInstructor(int instructorId, int percentage);

    @Query("select i from PermanentInstructor i where i.id=?1")
    PermanentInstructor getPermanentInstructorById(int id);
}