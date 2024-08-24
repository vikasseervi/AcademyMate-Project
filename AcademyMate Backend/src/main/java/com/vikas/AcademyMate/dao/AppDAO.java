package com.vikas.AcademyMate.dao;

import com.vikas.AcademyMate.entity.Course;
import com.vikas.AcademyMate.entity.Instructor;
import com.vikas.AcademyMate.entity.InstructorDetail;
import com.vikas.AcademyMate.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    List<Course> findAllCourses();

    List<Instructor> findAllInstructors();

    List<InstructorDetail> findAllInstructorDetails();

    List<Student> findAllStudents();

    Instructor findInstructorById(int id);

    Student findStudentById(int id);


    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCourseByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor newInstructor);

    void update(Course newCourse);

    Course findCourseById(int id);

    List<Course> findCoursesByStudentId(int studentId);

    void deleteCourseById(int id);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int id);

    Course findCourseAndStudentsByCourseId(int id);

    Student findStudentAndCoursesByStudentId(int id);

    Instructor findInstructorByCourseId(int courseId);

    void update(Student student);

    void deleteStudentById(int id);

    void save(Student student);

    void save(InstructorDetail instructorDetail);

    void deleteStudentFromCourse(int courseId, int studentId);

    void addStudentToCourse(int courseId, int studentId);
}
