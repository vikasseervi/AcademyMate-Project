package com.vikas.AcademyMate.dao.instructor;

import com.vikas.AcademyMate.entity.Instructor;
import java.util.List;

public interface InstructorDAO {

    void save(Instructor instructor);

    void update(Instructor instructor);

    List<Instructor> findAll();

    Instructor findById(int id);

    void deleteById(int id);

    Instructor findByIdJoinFetch(int id);

    Instructor findByCourseId(int courseId);
}
