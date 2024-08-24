package com.vikas.AcademyMate.entity;


import java.io.Serializable;
import java.util.Objects;

public class CourseStudentId implements Serializable {
    private int courseId;
    private int studentId;

    public CourseStudentId() {}

    public CourseStudentId(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    // equals and hashCode methods should be implemented here as well
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudentId that = (CourseStudentId) o;
        return courseId == that.courseId && studentId == that.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }
}