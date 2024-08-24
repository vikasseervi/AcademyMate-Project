import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/Header/Header';
import AddInstructor from './components/Instructor/AddInstructor';
import InstructorList from './components/Instructor/InstructorList';
import CourseList from './components/Course/CourseList';
import StudentList from './components/Student/StudentList';
import AddStudent from './components/Student/AddStudent';
import AddCourse from './components/Course/AddCourse';
import CourseDetails from './components/Course/CourseDetails';
import StudentDetails from './components/Student/StudentDetails';
import InstructorDetails from './components/Instructor/InstructorDetails';

function App() {
  return (
    <Router>
      <div>
        <Header />
        <Routes>

          {/* Home Route */}
          <Route path="/academymate" element={
            <div>
              <h2>Welcome to AcademyMate</h2>
            </div>
          } />

          {/* Routes */}
          <Route path="/academymate/students" element={<StudentList />} />
          <Route path="/academymate/students/:id" element={<StudentDetails />} />
          <Route path="/academymate/students/add-student" element={<AddStudent />} />
          <Route path="/academymate/students/update/:id" element={<AddStudent />} />

          <Route path="/academymate/instructors" element={<InstructorList />} />
          <Route path="/academymate/instructors/:id" element={<InstructorDetails />} />
          <Route path="/academymate/instructors/add-instructor" element={<AddInstructor />} />
          <Route path="/academymate/instructors/update/:id" element={<AddInstructor />} />

          <Route path="/academymate/courses" element={<CourseList />} />
          <Route path="/academymate/courses/add-course" element={<AddCourse />} />
          <Route path="/academymate/courses/:id" element={<CourseDetails />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
