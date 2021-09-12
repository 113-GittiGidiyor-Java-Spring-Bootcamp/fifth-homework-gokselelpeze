package goksel.elpeze.hw5.mappers;

import goksel.elpeze.hw5.dto.CourseDTO;
import goksel.elpeze.hw5.model.Course;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper {

    Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);

    CourseDTO mapFromCoursetoCourseDTO(Course course);

}
