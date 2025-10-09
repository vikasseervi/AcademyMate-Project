package com.vikas.AcademyMate.dao.student;

import com.vikas.AcademyMate.entity.Course;
import com.vikas.AcademyMate.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Course> findCoursesByStudentId(int studentId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c join c.students s where s.id = :studentId",
                Course.class
        );
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    public Student findStudentAndCoursesById(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s JOIN FETCH s.courses where s.id = :data",
                Student.class
        );
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}
