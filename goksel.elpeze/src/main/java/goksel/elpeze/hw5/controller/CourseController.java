package goksel.elpeze.hw5.controller;

import goksel.elpeze.hw5.dto.CourseDTO;
import goksel.elpeze.hw5.model.Course;
import goksel.elpeze.hw5.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<?> findAllCourses() {
        return new ResponseEntity<>(courseService.findAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<?> findCourseById(@PathVariable int courseId) {
        return new ResponseEntity<>(courseService.findCourseById(courseId), HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<?> saveCourse(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<>(courseService.saveCourse(courseDTO),HttpStatus.OK);
    }

    @PutMapping("/courses")
    public ResponseEntity<?> updateCourse(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<>(courseService.saveCourse(courseDTO),HttpStatus.OK);
    }

    @DeleteMapping("/courses")
    public ResponseEntity<String> deleteCourse(@RequestBody Course course) {
        int courseId = course.getId();
        if (courseService.existsById(courseId)) {
            courseService.deleteCourse(course);
            return new ResponseEntity<>("Course with id: " + courseId + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course with id: " + courseId + " not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable int courseId) {
        if (courseService.existsById(courseId)) {
            courseService.deleteCourseById(courseId);
            return new ResponseEntity<>("Course with id: " + courseId + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course with id: " + courseId + " not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/courses/byName")
    public ResponseEntity<?> findCoursesByCourseName(@RequestParam String courseName) {
        return new ResponseEntity<>(courseService.findCoursesByCourseName(courseName), HttpStatus.OK);
    }

    @DeleteMapping("/courses/byName")
    public ResponseEntity<String> deleteCoursesCourseName(@RequestParam String courseName) {
        if (courseService.findCoursesByCourseName(courseName).isEmpty()) {
            return new ResponseEntity<>("Course: " + courseName + " not found.", HttpStatus.BAD_REQUEST);
        } else {
            courseService.deleteCoursesByCourseName(courseName);
            return new ResponseEntity<>("Course(s): " + courseName + " deleted.", HttpStatus.OK);
        }
    }
}
