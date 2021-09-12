package goksel.elpeze.hw5.repository;

import goksel.elpeze.hw5.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {


    @Query("select count(e) from Course e")
    int getNumberOfCourses();

    List<Course> findCoursesByCourseName(String courseName);

    @Query("select c from Course c WHERE  c.courseCode= ?1")
    Course findCourseByCourseCode(String courseCode);

    void deleteCoursesByCourseName(String courseName);

}
