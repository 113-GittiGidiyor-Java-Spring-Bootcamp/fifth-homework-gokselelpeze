package goksel.elpeze.hw5.service;

import goksel.elpeze.hw5.dto.StudentDTO;
import goksel.elpeze.hw5.exceptions.StudentAgeNotValidException;
import goksel.elpeze.hw5.mappers.StudentMapper;
import goksel.elpeze.hw5.model.Student;
import goksel.elpeze.hw5.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    public boolean existsById(int studentId) {
        return studentRepository.existsById(studentId);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    /**
     * Check student's if its age is appropriate for school
     * If not throw exception
     * Else save to db
     *
     * @param studentDTO
     * @return Optional<Student>
     */
    @Transactional
    public Optional<Student> saveStudent(StudentDTO studentDTO) {
        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        int age = Period.between(student.getBirthdate(), LocalDate.now()).getYears();
        if (age < 18 || age > 40) {
            throw new StudentAgeNotValidException("Student " + student.getName() + "'s age is not appropriate!");
        }
        return Optional.of(studentRepository.save(student));
    }

    @Transactional
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Transactional
    public void deleteStudentById(int studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> findStudentsByName(String name) {
        return studentRepository.findStudentsByName(name);
    }

    public List<?> getStudentsGendersWithGrouping() {
        return studentRepository.getStudentsGendersWithGrouping();
    }

    @Transactional
    public void deleteStudentsByName(String name) {
        studentRepository.deleteStudentsByName(name);
    }

    public Set<Student> findAllStudentsById(List<Integer> studentIds) {
        Set<Student> students = new HashSet<>();

        for (int id : studentIds) {
            students.add(studentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Student with id: " + id + " can not be found!")));
        }
        return students;

    }
    public List<Integer> findAllStudentIdsByList(Set<Student> students) {

        List<Integer> studentIds = new ArrayList<>();

        for (Student s : students) {
            studentIds.add(s.getId());
        }
        return studentIds;
    }
}
