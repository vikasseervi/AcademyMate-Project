import { useState, useEffect } from 'react';
import { addStudent, getStudentById, updateStudent } from '../../services/apiService';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Link, useParams } from 'react-router-dom';
import './AddStudent.css'

const AddStudent = () => {
  const [student, setStudent] = useState({
    firstName: '',
    lastName: '',
    email: '',
    courses: []
  });

  const { id } = useParams();

  useEffect(() => {
    if (id) {
      fetchStudentById(id);
    }
  }, [id]);

  const fetchStudentById = async (studentId) => {
    try {
      const response = await getStudentById(studentId);
      setStudent(response.data);
    }
    catch (error) {
      console.error('Error fetching student data:', error);
    }
  };
  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent((prevStudent) => ({
      ...prevStudent,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!student.firstName || !student.lastName || !student.email) {
      toast.error('Please fill in all required fields!');
      return;
    }

    try {

      if(id) {
        await updateStudent(id, student);
      }
      else await addStudent(student);

      setStudent({
        firstName: '',
        lastName: '',
        email: '',
        courses: []
      });
      toast.success(id ? 'Student updated successfully!' : 'Student added successfully!');
    }
    catch (error) {
      console.error("There was an error creating the student!", error);
    }
  };

  return (
    <div>
      <ToastContainer />
      <h2 className='student-h2'>Add Student</h2>
      <form className="student-form" onSubmit={handleSubmit}>
        <div>
          <label htmlFor="firstName">First Name</label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            placeholder="First Name"
            value={student.firstName}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="lastName">Last Name</label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            placeholder="Last Name"
            value={student.lastName}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            placeholder="Email"
            value={student.email}
            onChange={handleChange}
          />
        </div>
        <button type="submit">{id ? 'Update Student' : 'Add Student'}</button>
      </form>

      <hr />
      
      <div style={{ marginTop: '20px' }}>
        <Link to={'/academymate/students'} className='student-link'>Back to Student</Link>
      </div>
    </div>
  );
};

export default AddStudent;
