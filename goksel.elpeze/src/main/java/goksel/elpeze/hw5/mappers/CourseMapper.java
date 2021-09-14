package goksel.elpeze.hw5.mappers;

import goksel.elpeze.hw5.dto.CourseDTO;
import goksel.elpeze.hw5.model.Course;
import goksel.elpeze.hw5.service.InstructorService;
import goksel.elpeze.hw5.service.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    @Autowired
    InstructorService instructorService;

    @Autowired
    StudentService studentService;

    @Mapping(target = "instructor", expression = "java((instructorService.getCourseInstructorById(courseDTO.getInstructorId())))")
    @Mapping(target = "students",expression = "java(studentService.findAllStudentsById(courseDTO.getStudentIds()))")
    public abstract Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);

    @Mapping(target = "instructorId", expression = "java(course.getInstructor().getId())")
    @Mapping(target = "studentIds", expression = "java(studentService.findAllStudentIdsByList(course.getStudents()))")
    public abstract CourseDTO mapFromCoursetoCourseDTO(Course course);

}