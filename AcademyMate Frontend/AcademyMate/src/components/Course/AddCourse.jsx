import { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { addCourse, getCourseById, updateCourse, getInstructors } from '../../services/apiService';
import './AddCourse.css';

const AddCourse = () => {
  const [course, setCourse] = useState({
    title: '',
    instructorId: ''
  });

  const [instructors, setInstructors] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    fetchInstructors();
    if (id) {
      fetchCourseById(id);
    }
  }, [id]);

  const fetchInstructors = async () => {
    try {
      const response = await getInstructors();
      setInstructors(response.data);
    } catch (error) {
      console.error('Error fetching instructors:', error);
    }
  };

  const fetchCourseById = async (courseId) => {
    try {
      const response = await getCourseById(courseId);
      setCourse(response.data);
    } catch (error) {
      console.error('Error fetching course data:', error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCourse((prevCourse) => ({
      ...prevCourse,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!course.title || !course.instructorId) {
      toast.error('Please fill in all required fields!');
      return;
    }

    try {
      if (id) {
        await updateCourse(id, course);
      } else {
        await addCourse(course);
      }

      setCourse({
        title: '',
        instructorId: ''
      });

      toast.success(id ? 'Course updated successfully!' : 'Course added successfully!');
    } catch (error) {
      console.error("There was an error creating the course!", error);
    }
  };

  return (
    <div>
      <ToastContainer />
      <h2>{id ? "Update Course" : "Add Course"}</h2>
      <form onSubmit={handleSubmit} className='course-form'>
        <div>
          <label>Course Title</label>
          <input type="text" name="title" placeholder="Course Title" value={course.title} onChange={handleChange} />
        </div>
        <div>
          <label>Instructor</label>
          <select name="instructorId" value={course.instructorId} onChange={handleChange} className="course-select">
            <option value="">Select Instructor</option>
            {instructors.map((instructor) => (
              <option key={instructor.id} value={instructor.id}>
                {instructor.firstName} {instructor.lastName}
              </option>
            ))}
          </select>
        </div>
        <button type="submit">{id ? "Update Course" : "Add Course"}</button>
      </form>

      <hr />

      <div>
        <Link to="/academymate/courses" className='Link'>Back to Courses</Link>
      </div>
    </div>
  );
};

export default AddCourse;
