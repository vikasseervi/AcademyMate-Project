package com.vikas.AcademyMate.dao.course;

import com.vikas.AcademyMate.entity.Course;
import com.vikas.AcademyMate.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {

    private final EntityManager entityManager;

    @Autowired
    public CourseDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager.createQuery("from Course", Course.class);
        return query.getResultList();
    }

    @Override
    public Course findById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    public List<Course> findByInstructorId(int instructorId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c where c.instructor.id = :data",
                Course.class
        );
        query.setParameter("data", instructorId);
        return query.getResultList();
    }

    @Override
    public Course findCourseAndReviewsById(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c JOIN FETCH c.reviews where c.id = :data",
                Course.class
        );
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsById(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c JOIN FETCH c.students where c.id = :data",
                Course.class
        );
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteStudentFromCourse(int courseId, int studentId) {
        com.vikas.AcademyMate.entity.CourseStudent courseStudent = entityManager.find(com.vikas.AcademyMate.entity.CourseStudent.class, new com.vikas.AcademyMate.entity.CourseStudentId(courseId, studentId));
        if (courseStudent != null) {
            entityManager.remove(courseStudent);
        }
    }

    @Override
    @Transactional
    public void addStudentToCourse(int courseId, int studentId) {
        com.vikas.AcademyMate.entity.CourseStudent newCourseStudent = new com.vikas.AcademyMate.entity.CourseStudent(courseId, studentId);
        entityManager.persist(newCourseStudent);
    }
}
