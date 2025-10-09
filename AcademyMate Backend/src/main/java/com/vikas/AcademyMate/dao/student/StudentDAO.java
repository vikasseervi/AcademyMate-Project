package com.vikas.AcademyMate.dao.student;

import com.vikas.AcademyMate.entity.Course;
import com.vikas.AcademyMate.entity.Student;
import java.util.List;

public interface StudentDAO {

    void save(Student student);

    void update(Student student);

    List<Student> findAll();

    Student findById(int id);

    List<Course> findCoursesByStudentId(int studentId);

    Student findStudentAndCoursesById(int id);

    void deleteById(int id);
}
