package com.vikas.AcademyMate.dao.course;

import com.vikas.AcademyMate.entity.Course;
import java.util.List;

public interface CourseDAO {

    void save(Course course);

    void update(Course course);

    List<Course> findAll();

    Course findById(int id);

    void deleteById(int id);

    List<Course> findByInstructorId(int instructorId);

    Course findCourseAndReviewsById(int id);

    Course findCourseAndStudentsById(int id);

    void deleteStudentFromCourse(int courseId, int studentId);

    void addStudentToCourse(int courseId, int studentId);
}
