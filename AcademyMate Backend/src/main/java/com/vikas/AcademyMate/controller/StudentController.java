package com.vikas.AcademyMate.controller;

import com.vikas.AcademyMate.dao.student.StudentDAO;
import com.vikas.AcademyMate.entity.Course;
import com.vikas.AcademyMate.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academymate/students")
public class StudentController {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        studentDAO.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentDAO.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentDAO.findById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudentId(@PathVariable int id) {
        List<Course> courses = studentDAO.findCoursesByStudentId(id);
        for(Course course : courses){
            course.setInstructor(null);
            course.setReviews(null);
            course.setStudents(null);
        }
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        studentDAO.update(updatedStudent);
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
        studentDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Student> studentDetails(@PathVariable int id) {
        Student student = studentDAO.findStudentAndCoursesById(id);
//        System.out.println(student);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
