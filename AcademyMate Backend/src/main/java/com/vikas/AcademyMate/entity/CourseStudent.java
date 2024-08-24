package com.vikas.AcademyMate.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course_student")
@IdClass(CourseStudentId.class)
public class CourseStudent {

    @Id
    @Column(name = "course_id")
    private int courseId;

    @Id
    @Column(name = "student_id")
    private int studentId;

    public CourseStudent() {}

    public CourseStudent(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudent that = (CourseStudent) o;
        return courseId == that.courseId && studentId == that.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }
}

