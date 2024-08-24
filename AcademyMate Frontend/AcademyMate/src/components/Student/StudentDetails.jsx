import { useEffect, useState } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { getStudentById, getCoursesByStudentId} from '../../services/apiService'
import './StudentDetails.css'

const StudentDetails = () => {
  const { id } = useParams();
  const [student, setStudent] = useState(null);
  const [courses, setCourses] = useState([]);
  const navigate = useNavigate(); 

  useEffect(() => {
    fetchStudentDetails(id);
  }, [id]);

  const fetchStudentDetails = async (studentId) => {
    try {
      const studentResponse = await getStudentById(studentId);
      const studentCourseResponse = await getCoursesByStudentId(studentId);
      setStudent(studentResponse.data);
      setCourses(studentCourseResponse.data);
    } 
    catch (error) {
      console.error('Error fetching student details:', error);
    }
  };

  if (!student) return <div>Loading...</div>;

  return (
    <div className="student-details-container">
      <h3>Student Details</h3>
      <p><span> First Name: </span> {student.firstName} </p>
      <p><span> Last Name: </span>{student.lastName}</p>
      <p><span>Email: </span>{student.email}</p>

      <h3>Enrolled Courses</h3>
      <ul>
        {courses.length > 0 ? (
          courses.map(course => (
            <li key={course.id} onClick={() => navigate(`/academymate/courses/${course.id}`)}>{course.title}</li>
          ))
        ) : (
          <li>No courses enrolled</li>
        )}
      </ul>

      <div>
        <Link to="/academymate/students/">Back to Students</Link>
      </div>
    </div>
  );
};

export default StudentDetails;
