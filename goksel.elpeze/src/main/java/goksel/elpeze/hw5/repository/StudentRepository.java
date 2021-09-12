package goksel.elpeze.hw5.repository;

import goksel.elpeze.hw5.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentsByName(String name);

    @Query("select s.gender, count(s.gender) from Student s GROUP BY s.gender")
    List<?> getStudentsGendersWithGrouping();

    void deleteStudentsByName(String name);


}
