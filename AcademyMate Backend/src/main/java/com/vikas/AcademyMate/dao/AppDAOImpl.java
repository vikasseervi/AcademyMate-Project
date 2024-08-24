package com.vikas.AcademyMate.dao;

import com.vikas.AcademyMate.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public List<Instructor> findAllInstructors() {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "from Instructor", Instructor.class
        );
        return query.getResultList();
    }

    @Override
    public List<InstructorDetail> findAllInstructorDetails( ) {
        TypedQuery<InstructorDetail> query = entityManager.createQuery(
                "from InstructorDetail", InstructorDetail.class
        );
        return query.getResultList();
    }

    @Override
    public List<Student> findAllStudents( ) {
        TypedQuery<Student> query = entityManager.createQuery(
                "from Student", Student.class
        );
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        List<Course> courses = instructor.getCourses();
        // break associations of all courses for instructor
        for(Course course : courses){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);
        // remove the associated object reference
        // break bi-direction link
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int id) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);
        // execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select inst from Instructor inst " +
                        "JOIN FETCH inst.courses " +
                        "JOIN FETCH inst.instructorDetail " +
                        "where inst.id = :data", Instructor.class);
        query.setParameter("data", id);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor newInstructor) {
        entityManager.merge(newInstructor);
    }

    @Override
    public List<Course> findAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course", Course.class
        );
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Course newCourse) {
        entityManager.merge(newCourse);
    }


    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Course> findCoursesByStudentId(int studentId) {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.id IN (SELECT cs.courseId FROM CourseStudent cs WHERE cs.studentId = :studentId)", Course.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "JOIN FETCH c.reviews "+
                        "where c.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "JOIN FETCH c.students "+
                        "where c.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s " +
                        "JOIN FETCH s.courses "+
                        "where s.id = :data", Student.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    public Instructor findInstructorByCourseId(int courseId) {
        try {
            TypedQuery<Instructor> query = entityManager.createQuery(
                    "select i from Instructor i JOIN FETCH i.courses c where c.id = :courseId", Instructor.class);
            query.setParameter("courseId", courseId);
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteStudentFromCourse(int courseId, int studentId) {
        CourseStudent courseStudent = entityManager.find(CourseStudent.class, new CourseStudentId(courseId, studentId));

        if (courseStudent != null) {
            entityManager.remove(courseStudent);
        }
    }

    @Override
    @Transactional
    public void addStudentToCourse(int courseId, int studentId) {
        CourseStudent newCourseStudent = new CourseStudent(courseId, studentId);
        entityManager.persist(newCourseStudent);
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    @Transactional
    public void save(InstructorDetail instructorDetail) {
        entityManager.persist(instructorDetail);
    }

}
