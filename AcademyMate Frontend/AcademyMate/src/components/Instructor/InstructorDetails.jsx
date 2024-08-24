import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { getInstructorById } from '../../services/apiService';
import './InstructorDetails.css'

const InstructorDetails = () => {
  const { id } = useParams();
  const [instructor, setInstructor] = useState(null);

  useEffect(() => {
    fetchInstructorDetails(id);
  }, [id]);

  const fetchInstructorDetails = async (instructorId) => {
    try {
      const response = await getInstructorById(instructorId);
      setInstructor(response.data);
    }
    catch (error) {
      console.error('Error fetching instructor details:', error);
    }
  };

  if (!instructor) return <div>Loading...</div>;

  return (
    <div className="instructor-details-container">
      <h2>{instructor.firstName} {instructor.lastName}</h2>
      <p>Email: {instructor.email}</p>

      <h3>Instructor Details</h3>
      <ul>
        <li>YouTube Channel: {instructor.instructorDetail.youtubeChannel}</li>
        <li>Hobby: {instructor.instructorDetail.hobby}</li>
      </ul>

      <div style={{ marginTop: '50px' }}>
        <Link to="/academymate/instructors/">Back to Instructors</Link>
      </div>
    </div>
  );
};

export default InstructorDetails;
