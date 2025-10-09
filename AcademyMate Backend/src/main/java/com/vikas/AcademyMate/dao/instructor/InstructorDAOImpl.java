package com.vikas.AcademyMate.dao.instructor;

import com.vikas.AcademyMate.entity.Course;
import com.vikas.AcademyMate.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstructorDAOImpl implements InstructorDAO {

    private final EntityManager entityManager;

    @Autowired
    public InstructorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public List<Instructor> findAll() {
        TypedQuery<Instructor> query = entityManager.createQuery("from Instructor", Instructor.class);
        return query.getResultList();
    }

    @Override
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        List<Course> courses = instructor.getCourses();
        for (Course course : courses) {
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public Instructor findByIdJoinFetch(int id) {
        try {
            TypedQuery<Instructor> query = entityManager.createQuery(
                    "select i from Instructor i JOIN FETCH i.courses where i.id = :data", Instructor.class);
            query.setParameter("data", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Instructor findByCourseId(int courseId) {
        try {
            TypedQuery<Instructor> query = entityManager.createQuery(
                    "select i from Instructor i JOIN FETCH i.courses c where c.id = :courseId", Instructor.class);
            query.setParameter("courseId", courseId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
