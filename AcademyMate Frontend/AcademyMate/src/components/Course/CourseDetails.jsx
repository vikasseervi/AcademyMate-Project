import { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { getCourseById, updateCourse, getInstructorsByCourseId, getInstructors, deleteStudentFromCourse, addStudentToCourse, getStudents, deleteCourse } from '../../services/apiService';
import './CourseDetails.css';

const CourseDetails = () => {
  const { id } = useParams();
  const [course, setCourse] = useState(null);
  const [students, setStudents] = useState([]);
  const [instructors, setInstructors] = useState([]);
  const [selectedInstructor, setSelectedInstructor] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    fetchCourseDetails(id);
  }, [id]);

  const fetchCourseDetails = async (courseId) => {
    try {
      const courseResponse = await getCourseById(courseId);
      const allStudentsResponse = await getStudents();
      const courseInstructorsResponse = await getInstructorsByCourseId(courseId);
      const instructorsResponse = await getInstructors();

      const addedStudents = courseResponse.data.students;
      const nonAddedStudents = allStudentsResponse.data.filter(
        (student) => !addedStudents.some((s) => s.id === student.id)
      );

      setCourse(courseResponse.data);
      setStudents([...addedStudents, ...nonAddedStudents]); // Add added students at the top
      setSelectedInstructor(courseInstructorsResponse.data);
      setInstructors(instructorsResponse.data);
    } catch (error) {
      console.error('Error fetching course details:', error);
    }
  };

  const handleInstructorChange = async (e) => {
    const newInstructorId = Number(e.target.value);
    const newSelectedInstructor = instructors.find(instructor => instructor.id === newInstructorId);
    console.log('New Selected Instructor:', newSelectedInstructor);
    setSelectedInstructor(newSelectedInstructor);
    const updatedCourse = { ...course, instructor: newSelectedInstructor };
    await updateCourse(id, updatedCourse);
  };

  const handleRemoveStudent = async (studentId) => {
    try {
      await deleteStudentFromCourse(id, studentId);
      fetchCourseDetails(id);
    } catch (error) {
      console.error("Error removing student from course:", error);
    }
  };

  const handleAddStudent = async (studentId) => {
    try {
      await addStudentToCourse(id, studentId);
      fetchCourseDetails(id);
    } catch (error) {
      console.error("Error adding student to course:", error);
    }
  };
  
  const handleDeleteCourse = async () => {
    const confirmed = window.confirm("Are you sure you want to delete this course?");
    if (confirmed) {
      try {
        await deleteCourse(id);
        navigate("/academymate/courses");
      } catch (error) {
        console.error("Error deleting course:", error);
      }
    }
  };

  if (!course) return <div>Loading...</div>;

  return (
    <div className="course-details-container">
      <div className="course-header">
        <h2 className='course-h2'>
          <u>{course.title}</u>
        </h2>
        <button className="delete-course-button" onClick={handleDeleteCourse}>
          Delete Course
        </button>
      </div>

      <div className="course-instructor">
        <h3>Instructor:</h3>
        <select name="instructorId" value={selectedInstructor.id} onChange={handleInstructorChange}>
          {instructors.map((instructor) => (
            <option key={instructor.id} value={instructor.id}>
              {instructor.firstName} {instructor.lastName}
            </option>
          ))}
        </select>
      </div>

      <div className="course-students">
        <h3>Students:</h3>
        <ul>
          {students.map((student) => (
            <li key={student.id}>
              <Link to={`/academymate/students/${student.id}`}>
                {student.firstName} {student.lastName}
              </Link>
              {course.students.some((s) => s.id === student.id) ? (
                <button onClick={() => handleRemoveStudent(student.id)} className='remove-button'>Remove</button>
              ) : (
                <button onClick={() => handleAddStudent(student.id)} className='add-button'>Add</button>
              )}
            </li>
          ))}
        </ul>
      </div>

      <hr />

      <div className="navigation-links">
        <Link to="/academymate/courses" className='Link'>Back to Courses</Link>
      </div>
    </div>
  );
};

export default CourseDetails;
