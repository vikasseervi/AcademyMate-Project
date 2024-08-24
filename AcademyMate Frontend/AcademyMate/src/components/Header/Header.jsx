import { Link, NavLink } from 'react-router-dom';
import './Header.css';

function Header() {
  return (
    <header className="header">
      <div className="header-left">
        <Link to="/academymate" className="header-link active">AcademyMate</Link>
      </div>
      <div className="header-center">
        <NavLink 
          to="academymate/students" 
          className={({ isActive }) => isActive ? "header-link active" : "header-link"}
        >
          Students
        </NavLink>
        <NavLink 
          to="academymate/instructors" 
          className={({ isActive }) => isActive ? "header-link active" : "header-link"}
        >
          Instructors
        </NavLink>
        <NavLink 
          to="academymate/courses" 
          className={({ isActive }) => isActive ? "header-link active" : "header-link"}
        >
          Courses
        </NavLink>
      </div>
      <div className="header-right">
        <button className="logout-button">Logout</button>
      </div>
    </header>
  );
}

export default Header;
