import { useEffect, useState } from 'react';
import { getCourses } from '../../services/apiService';
import './CourseList.css';
import { Link, useNavigate } from 'react-router-dom';

const CourseList = () => {
  const [courses, setCourses] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [coursesPerPage] = useState(10);
  const [sortConfig, setSortConfig] = useState({ key: 'title', direction: 'ascending' });
  const navigate = useNavigate();

  const handleAddCourse = () => {
    navigate('add-course');
  };

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await getCourses();
        setCourses(response.data);
      } catch (error) {
        console.error("Error fetching courses:", error);
      }
    };

    fetchCourses();
  }, []);

  const sortedCourses = [...courses].sort((a, b) => {
    if (a[sortConfig.key] < b[sortConfig.key]) {
      return sortConfig.direction === 'ascending' ? -1 : 1;
    }
    if (a[sortConfig.key] > b[sortConfig.key]) {
      return sortConfig.direction === 'ascending' ? 1 : -1;
    }
    return 0;
  });

  const indexOfLastCourse = currentPage * coursesPerPage;
  const indexOfFirstCourse = indexOfLastCourse - coursesPerPage;
  const currentCourses = sortedCourses.slice(indexOfFirstCourse, indexOfLastCourse);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const requestSort = (key) => {
    let direction = 'ascending';
    if (sortConfig.key === key && sortConfig.direction === 'ascending') {
      direction = 'descending';
    }
    setSortConfig({ key, direction });
  };

  return (
    <div className="course-list-container">
      <button className="add-course-button" onClick={handleAddCourse}>Add Course</button>
      <table className="course-table">
        <thead>
          <tr className="thead">
            <th onClick={() => requestSort('title')}>Course Name</th>
            <th>Reviews</th>
          </tr>
        </thead>
        <tbody>
          {currentCourses.map((course) => (
            <tr key={course.id}>
              <td> <Link className='Link' to={`${course.id}`}>{course.title}</Link> </td>
              <td>
                {course.reviews.length > 0 ? (
                  <ul>
                    {course.reviews.map((review, index) => (
                      <li key={index}>{review.comment}</li>
                    ))}
                  </ul>
                ) : (
                  <p>No reviews</p>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="pagination">
        {Array.from({ length: Math.ceil(courses.length / coursesPerPage) }, (_, index) => (
          <button key={index + 1} onClick={() => paginate(index + 1)}>
            {index + 1}
          </button>
        ))}
      </div>
    </div>
  );
};

export default CourseList;
