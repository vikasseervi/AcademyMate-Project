package com.vikas.AcademyMate.controller;

import com.vikas.AcademyMate.dao.AppDAO;
import com.vikas.AcademyMate.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academymate/instructor-detail")
public class InstructorDetailController {
    private final AppDAO appDAO;

    @Autowired
    public InstructorDetailController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @PostMapping
    public ResponseEntity<InstructorDetail> createInstructorDetail(@RequestBody InstructorDetail instructorDetail) {
        appDAO.save(instructorDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorDetail);
    }

    @GetMapping
    public ResponseEntity<List<InstructorDetail>> getAllInstructorDetail() {
        List<InstructorDetail> instructorDetails = appDAO.findAllInstructorDetails();
        return ResponseEntity.ok(instructorDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructorDetail(@PathVariable int id) {
        appDAO.deleteInstructorDetailById(id);
        return ResponseEntity.noContent().build();
    }
}
