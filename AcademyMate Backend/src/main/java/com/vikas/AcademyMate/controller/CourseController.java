package com.vikas.AcademyMate.controller;

import com.vikas.AcademyMate.dao.AppDAO;
import com.vikas.AcademyMate.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academymate/courses")
public class CourseController {

    private final AppDAO appDAO;

    @Autowired
    public CourseController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        appDAO.save(course); // Ensure save method is appropriate for Course
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        System.out.println("GETTING ALL COURSES");
        List<Course> courses = appDAO.findAllCourses(); // Assume this method is implemented in your DAO
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        Course course = appDAO.findCourseById(id); // Assume this method is implemented in your DAO
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        System.out.println(updatedCourse.toString());
        appDAO.update(updatedCourse);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<String> deleteStudentFromCourse(@PathVariable int courseId, @PathVariable int studentId) {
        try {
            appDAO.deleteStudentFromCourse(courseId, studentId);
            return ResponseEntity.ok("Student removed from course successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while removing the student from the course.");
        }
    }

    @PostMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<String> addStudentToCourse(@PathVariable int courseId, @PathVariable int studentId) {
        try {
            appDAO.addStudentToCourse(courseId, studentId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added to course successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the student to the course.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        appDAO.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }
}

