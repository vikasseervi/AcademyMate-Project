import { useEffect, useState } from 'react';
import { getInstructors, deleteInstructor } from '../../services/apiService';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate, Link } from 'react-router-dom';
import './InstructorList.css';

const InstructorList = () => {
  const [instructors, setInstructors] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [instructorsPerPage] = useState(10);
  const [sortConfig, setSortConfig] = useState({ key: 'firstName', direction: 'ascending' });
  const navigate = useNavigate();

  useEffect(() => {
    const fetchInstructors = async () => {
      try {
        const response = await getInstructors();
        setInstructors(response.data);
      } catch (error) {
        console.error("Error fetching instructors:", error);
      }
    };

    fetchInstructors();
  }, []);

  const handleAddInstructor = () => {
    navigate('add-instructor');
  };

  const handleUpdate = (instructorId) => {
    navigate(`update/${instructorId}`);
  };

  const handleDelete = async (instructorId) => {
    try {
      await deleteInstructor(instructorId);
      setInstructors(instructors.filter(instructor => instructor.id !== instructorId));
      toast.success('Instructor deleted successfully!');
    } catch (error) {
      console.error("Error deleting instructor:", error);
    }
  };

  const sortedInstructors = [...instructors].sort((a, b) => {
    if (a[sortConfig.key] < b[sortConfig.key]) {
      return sortConfig.direction === 'ascending' ? -1 : 1;
    }
    if (a[sortConfig.key] > b[sortConfig.key]) {
      return sortConfig.direction === 'ascending' ? 1 : -1;
    }
    return 0;
  });

  const indexOfLastInstructor = currentPage * instructorsPerPage;
  const indexOfFirstInstructor = indexOfLastInstructor - instructorsPerPage;
  const currentInstructors = sortedInstructors.slice(indexOfFirstInstructor, indexOfLastInstructor);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const requestSort = (key) => {
    let direction = 'ascending';
    if (sortConfig.key === key && sortConfig.direction === 'ascending') {
      direction = 'descending';
    }
    setSortConfig({ key, direction });
  };

  return (
    <div className="instructor-list-container">
      <ToastContainer />
      <button className="add-instructor-button" onClick={handleAddInstructor}>Add Instructor</button>
      <table className="instructor-table">
        <thead>
          <tr className="thead">
            <th onClick={() => requestSort('firstName')}>First Name</th>
            <th onClick={() => requestSort('lastName')}>Last Name</th>
            <th onClick={() => requestSort('email')}>Email</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {currentInstructors.map((instructor) => (
            <tr key={instructor.id}>
              <td> <Link to={`${instructor.id}`} > {instructor.firstName} </Link></td>
              <td> <Link to={`${instructor.id}`} > {instructor.lastName} </Link></td>
              <td>{instructor.email}</td>
              <td>
                <button 
                  className="action-button update-button" 
                  onClick={() => handleUpdate(instructor.id)}>
                  Update
                </button>
                <button 
                  className="action-button delete-button" 
                  onClick={() => handleDelete(instructor.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="pagination">
        {Array.from({ length: Math.ceil(instructors.length / instructorsPerPage) }, (_, index) => (
          <button key={index + 1} onClick={() => paginate(index + 1)}>
            {index + 1}
          </button>
        ))}
      </div>
    </div>
  );
};

export default InstructorList;
