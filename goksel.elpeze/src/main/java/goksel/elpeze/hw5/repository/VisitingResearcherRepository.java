package goksel.elpeze.hw5.repository;

import goksel.elpeze.hw5.model.VisitingResearcher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitingResearcherRepository extends InstructorRepository {

    @Query("UPDATE VisitingResearcher SET hourlySalary = hourlySalary + (hourlySalary * ?2) WHERE id=?1")
    void updateSalaryOfVisitingResearcher(int instructorId, int percentage);

    @Query("select i from VisitingResearcher i where i.id=?1")
    VisitingResearcher getVisitingResearcherById(int instructorId);
}
