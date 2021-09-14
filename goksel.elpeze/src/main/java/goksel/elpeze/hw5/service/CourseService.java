package goksel.elpeze.hw5.service;

import goksel.elpeze.hw5.dto.CourseDTO;
import goksel.elpeze.hw5.exceptions.CourseIsAlreadyExistException;
import goksel.elpeze.hw5.exceptions.StudentNumberForOneCourseExceedException;
import goksel.elpeze.hw5.mappers.CourseMapper;
import goksel.elpeze.hw5.model.Course;
import goksel.elpeze.hw5.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public boolean existsById(int courseId) {
        return courseRepository.existsById(courseId);
    }

    public List<CourseDTO> findAllCourses() {
        return courseRepository.findAll().stream().map(courseMapper::mapFromCoursetoCourseDTO).collect(Collectors.toList());
    }

    public CourseDTO findCourseById(int courseId) {
        return courseRepository.findById(courseId).map(courseMapper::mapFromCoursetoCourseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    /**
     * Check course is already existing
     * If not save to db
     * Else throw exception
     *
     * @param courseDTO
     * @return Optional<Course>
     */
    @Transactional
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        if (Objects.nonNull(courseRepository.findCourseByCourseCode(courseDTO.getCourseCode()))) {
            throw new CourseIsAlreadyExistException("Course " + courseDTO.getCourseName() + " is already exists!");
        } else if (courseDTO.getStudentIds().size() > 20) {
            throw new StudentNumberForOneCourseExceedException("Course " + courseDTO.getCourseName() + " is full!");
        } else{
            Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
            courseRepository.save(course);
            return courseMapper.mapFromCoursetoCourseDTO(course);
        }
    }

    @Transactional
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    @Transactional
    public void deleteCourseById(int courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<Course> findCoursesByCourseName(String courseName) {
        return courseRepository.findCoursesByCourseName(courseName);
    }

    @Transactional
    public void deleteCoursesByCourseName(String courseName) {
        courseRepository.deleteCoursesByCourseName(courseName);
    }

}
