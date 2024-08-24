package com.vikas.AcademyMate.controller;

import com.vikas.AcademyMate.dao.AppDAO;
import com.vikas.AcademyMate.entity.Course;
import com.vikas.AcademyMate.entity.Student; // Ensure this import matches your actual Student entity package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academymate/students")
public class StudentController {
    private final AppDAO appDAO;

    @Autowired
    public StudentController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        appDAO.save(student); // Ensure the save method works with Student
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = appDAO.findAllStudents(); // Ensure this method is implemented in your DAO
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = appDAO.findStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudentId(@PathVariable int id) {
        List<Course> courses = appDAO.findCoursesByStudentId(id);
        for(Course course : courses){
            course.setInstructor(null);
            course.setReviews(null);
            course.setStudents(null);
        }
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        appDAO.update(updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
//        Student student = appDAO.findStudentById(id); // Ensure this method is implemented in your DAO
//        if (student != null) {
//            appDAO.delete(student); // Ensure the delete method works with Student
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
        appDAO.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Student> studentDetails(@PathVariable int id) {
        Student student = appDAO.findStudentAndCoursesByStudentId(id);
//        System.out.println(student);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
