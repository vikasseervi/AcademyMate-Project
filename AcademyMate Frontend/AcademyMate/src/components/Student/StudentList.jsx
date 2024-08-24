import { useEffect, useState } from 'react';
import { getStudents, deleteStudent } from '../../services/apiService';
import { useNavigate, Link } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './StudentList.css';

const StudentList = () => {
  const [students, setStudents] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [studentsPerPage] = useState(10);
  const [sortConfig, setSortConfig] = useState({ key: 'firstName', direction: 'ascending' });
  const navigate = useNavigate();

  useEffect(() => {
    const fetchStudents = async () => {
      try {
        const response = await getStudents();
        setStudents(response.data);
      } catch (error) {
        console.error("Error fetching students:", error);
      }
    };

    fetchStudents();
  }, []);

  const handleAddStudent = () => {
    navigate('add-student');
  };

  const handleUpdate = (studentId) => {
    navigate(`update/${studentId}`);
  };

  const handleDelete = async (studentId) => {
    try {
      await deleteStudent(studentId);
      toast.success('Student deleted successfully!');
      setStudents(students.filter(student => student.id !== studentId));
    } catch (error) {
      console.error("Error deleting student:", error);
    }
  };

  const sortedStudents = [...students].sort((a, b) => {
    if (a[sortConfig.key] < b[sortConfig.key]) {
      return sortConfig.direction === 'ascending' ? -1 : 1;
    }
    if (a[sortConfig.key] > b[sortConfig.key]) {
      return sortConfig.direction === 'ascending' ? 1 : -1;
    }
    return 0;
  });

  const indexOfLastStudent = currentPage * studentsPerPage;
  const indexOfFirstStudent = indexOfLastStudent - studentsPerPage;
  const currentStudents = sortedStudents.slice(indexOfFirstStudent, indexOfLastStudent);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const requestSort = (key) => {
    let direction = 'ascending';
    if (sortConfig.key === key && sortConfig.direction === 'ascending') {
      direction = 'descending';
    }
    setSortConfig({ key, direction });
  };

  return (
    <div className="student-list-container">
      <ToastContainer />
      <button className="add-student-button" onClick={handleAddStudent}>Add Student</button>
      <table className="student-table">
        <thead>
          <tr className="thead">
            <th onClick={() => requestSort('firstName')}>First Name</th>
            <th onClick={() => requestSort('lastName')}>Last Name</th>
            <th onClick={() => requestSort('email')}>Email</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {currentStudents.map((student) => (
            <tr key={student.id}>
              <td> <Link to={`${student.id}`} className='Link'> {student.firstName} </Link></td>
              <td> <Link to={`${student.id}`} className='Link'> {student.lastName} </Link></td>
              <td>{student.email}</td>
              <td>
                <button 
                  className="action-button update-button" 
                  onClick={() => handleUpdate(student.id)}>
                  Update
                </button>
                <button 
                  className="action-button delete-button" 
                  onClick={() => handleDelete(student.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="pagination">
        {Array.from({ length: Math.ceil(students.length / studentsPerPage) }, (_, index) => (
          <button key={index + 1} onClick={() => paginate(index + 1)}>
            {index + 1}
          </button>
        ))}
      </div>
    </div>
  );
};

export default StudentList;
