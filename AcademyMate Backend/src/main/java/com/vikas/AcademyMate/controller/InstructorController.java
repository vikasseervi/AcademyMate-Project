package com.vikas.AcademyMate.controller;

import com.vikas.AcademyMate.dao.instructor.InstructorDAO;
import com.vikas.AcademyMate.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academymate/instructors")
public class InstructorController {

    private final InstructorDAO instructorDAO;

    @Autowired
    public InstructorController(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorDAO.findAll();
        return ResponseEntity.ok(instructors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getStudentById(@PathVariable int id) {
        Instructor instructor = instructorDAO.findById(id);
        if (instructor != null) {
            return ResponseEntity.ok(instructor);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Instructor> getInstructorByCourseId(@PathVariable int id) {
        Instructor instructor = instructorDAO.findByCourseId(id);
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        instructorDAO.save(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(instructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable int id) {
        instructorDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable int id, @RequestBody Instructor updatedInstructor) {
        instructorDAO.update(updatedInstructor);
        return ResponseEntity.ok(updatedInstructor);
    }
}
