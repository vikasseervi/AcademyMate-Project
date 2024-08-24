package com.vikas.AcademyMate.controller;

import com.vikas.AcademyMate.dao.AppDAO;
import com.vikas.AcademyMate.entity.Instructor;
import com.vikas.AcademyMate.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academymate/instructors")
public class InstructorController {

    private final AppDAO appDAO;

    @Autowired
    public InstructorController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = appDAO.findAllInstructors(); // Assume this method is implemented in your DAO
        return ResponseEntity.ok(instructors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getStudentById(@PathVariable int id) {
        Instructor instructor = appDAO.findInstructorById(id);
        if (instructor != null) {
            return ResponseEntity.ok(instructor);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Instructor> getInstructorByCourseId(@PathVariable int id) {
        Instructor instructor = appDAO.findInstructorByCourseId(id);
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        appDAO.save(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(instructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable int id) {
        appDAO.deleteInstructorById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable int id, @RequestBody Instructor updatedInstructor) {
        appDAO.update(updatedInstructor);
        return ResponseEntity.ok(updatedInstructor);
    }
}
