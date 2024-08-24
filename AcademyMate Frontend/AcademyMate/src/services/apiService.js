import axios from 'axios';

const API_URL = 'http://localhost:8080/academymate';

// Students
export const getStudents = () => {
  return axios.get(`${API_URL}/students`);
};

export const getStudentById = (id) => {
  return axios.get(`${API_URL}/students/${id}`);
};

export const getCoursesByStudentId = (id) => {
  return axios.get(`${API_URL}/students/${id}/courses`);
};

export const deleteStudent = (id) => {
  return axios.delete(`${API_URL}/students/${id}`);
};

export const updateStudent = (id, updatedStudent) => {
  return axios.put(`${API_URL}/students/${id}`, updatedStudent);
};

export const addStudent = (student) => {
  return axios.post(`${API_URL}/students`, student);
}


// Instructors
export const getInstructorById = async (id) => {
  return await axios.get(`${API_URL}/instructors/${id}`);
};

export const getInstructors = () => {
  return axios.get(`${API_URL}/instructors`);
};

export const addInstructor = (instructor) => {
  return axios.post(`${API_URL}/instructors`, instructor);
};

export const updateInstructor = async (id, updatedInstructor) => {
  return await axios.put(`${API_URL}/instructors/${id}`, updatedInstructor);
};

export const deleteInstructor = async (id) => {
  return await axios.delete(`${API_URL}/instructors/${id}`);
};

export const getInstructorsByCourseId = async (id) => {
  return await axios.get(`${API_URL}/instructors/course/${id}`);
};


// Instructor Details
export const addInstructorDetail = (instructorDetail) => {
  return axios.post(`${API_URL}/instructor-detail`, instructorDetail);
};


// Courses
export const getCourses = async () => {
  return await axios.get(`${API_URL}/courses`);
};

export const getCourseById = async (id) => {
  return await axios.get(`${API_URL}/courses/${id}`);
};

export const addCourse = (course) => {
  return axios.post(`${API_URL}/courses`, course);
};

export const updateCourse = async (id, updatedCourse) => {
  return await axios.put(`${API_URL}/courses/${id}`, updatedCourse);
};

export const deleteCourse = async (id) => {
  return await axios.delete(`${API_URL}/courses/${id}`);
};

export const deleteStudentFromCourse = async (courseId, studentId) => {
  return await axios.delete(`${API_URL}/courses/${courseId}/student/${studentId}`);
}

export const addStudentToCourse = async (courseId, studentId) => {
  return await axios.post(`${API_URL}/courses/${courseId}/student/${studentId}`);
}