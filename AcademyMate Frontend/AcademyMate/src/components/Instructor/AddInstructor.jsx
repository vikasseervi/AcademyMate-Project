import { useState, useEffect } from 'react';
import { Link, useParams, } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { addInstructor, getInstructorById, updateInstructor } from '../../services/apiService';
import './AddInstructor.css';

const AddInstructor = () => {
  
  const [instructor, setInstructor] = useState({
    firstName: '',
    lastName: '',
    email: '',
    instructorDetail: {
      youtubeChannel: '',
      hobby: ''
    }
  });

  const {id} = useParams();

  useEffect(() => {
    if (id) {
      fetchInstructorById(id);
    }
  }, [id]);

  const fetchInstructorById = async (instructorId) => {
    try {
      const response = await getInstructorById(instructorId);
      setInstructor(response.data);
    } catch (error) {
      console.error('Error fetching instructor data:', error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setInstructor((prevInstructor) => {
      if (name in prevInstructor.instructorDetail) {
        return {
          ...prevInstructor,
          instructorDetail: {
            ...prevInstructor.instructorDetail,
            [name]: value
          }
        };
      } else {
        return { ...prevInstructor, [name]: value };
      }
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!instructor.firstName || !instructor.lastName || !instructor.email || !instructor.instructorDetail.hobby) {
      toast.error('Please fill in all required fields!');
      return;
    }
    
    try {
      
      if (id) {
        await updateInstructor(id, instructor);
      } 
      else {
        await addInstructor(instructor);
      }

      setInstructor({
        firstName: '',
        lastName: '',
        email: '',
        instructorDetail: {
          youtubeChannel: '',
          hobby: ''
        }
      });

      toast.success(id ? 'Instructor updated successfully!' : 'Instructor added successfully!');
    } 
    catch (error) {
      console.error("There was an error creating the instructor!", error);
    }
  };

  return (
    <div>
      <ToastContainer />
      <h2>Add Instructor</h2>
      <form onSubmit={handleSubmit} className='instructor-form'>
        <div>
          <label>First Name</label>
          <input
            type="text"
            name="firstName"
            placeholder="First Name"
            value={instructor.firstName}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Last Name</label>
          <input
            type="text"
            name="lastName"
            placeholder="Last Name"
            value={instructor.lastName}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Email</label>
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={instructor.email}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>YouTube Channel</label>
          <input
            type="text"
            name="youtubeChannel"
            placeholder="YouTube Channel"
            value={instructor.instructorDetail.youtubeChannel}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Hobby</label>
          <input
            type="text"
            name="hobby"
            placeholder="Hobby"
            value={instructor.instructorDetail.hobby}
            onChange={handleChange}
          />
        </div>
        <button type="submit">{id ? "Update Instructor" : "Add Instructor"}</button>
      </form>
      
      <hr />

      <div>
        <Link to="/academymate/instructors" className='instructor-link'> Back to Instructors </Link>
      </div>
    </div>
  );
};

export default AddInstructor;
