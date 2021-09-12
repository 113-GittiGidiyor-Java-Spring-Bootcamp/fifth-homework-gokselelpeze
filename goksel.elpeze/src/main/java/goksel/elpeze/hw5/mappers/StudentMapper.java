package goksel.elpeze.hw5.mappers;

import goksel.elpeze.hw5.dto.StudentDTO;
import goksel.elpeze.hw5.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract Student mapFromStudentDTOtoStudent(StudentDTO studentDTO);

    public abstract StudentDTO mapFromStudenttoStudentDTO(Student student);

}
