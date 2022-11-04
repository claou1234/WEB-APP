package gr.aueb.sev.service;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.sev.dao.ICourseDAO;
import gr.aueb.sev.dto.CourseDTO;
import gr.aueb.sev.model.Course;
import gr.aueb.sev.service.exceptions.CourseNotFoundException;

public class CourseServiceImpl implements ICourseService {
	
	private final ICourseDAO courseDAO;
	
	public CourseServiceImpl(ICourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public void insertCourse(CourseDTO courseDTO) throws SQLException{
		Course course = extract(courseDTO);
		
		try {
			courseDAO.insert(course);
			
		} catch (SQLException e) {
			throw e;
			
		}
		
	}

	@Override
	public void deleteCourse(CourseDTO courseDTO) throws SQLException, CourseNotFoundException{
		Course courseToDelete = extract(courseDTO);
		
		try {
			
			if(courseDAO.delete(courseToDelete) == null) {
				throw new CourseNotFoundException(courseToDelete);
			}
			
		} catch (SQLException e) {
			throw e;
			
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateCourse(CourseDTO oldCourseDTO, CourseDTO newCourseDTO) throws SQLException {
		Course oldCourse = extract(oldCourseDTO);
		Course newCourse = extract(newCourseDTO);
		
		try {
			
			courseDAO.update(oldCourse, newCourse);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public List<Course> getCourseByDescription(String description) throws SQLException {
		
		try {
			return courseDAO.getCoursesByDescription(description);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		}
	}

	@Override
	public Course getCourseById(int id) throws SQLException, CourseNotFoundException {
		
		
		try {
			Course course = courseDAO.getCourseById(id);
			if(course == null) {
				throw new CourseNotFoundException(course);
			} else {
				return course;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private Course extract(CourseDTO courseDTO) {
		Course course = new Course();
		course.setId(courseDTO.getId());
		course.setDescription(courseDTO.getDescription());
		course.setTeacherId(courseDTO.getTeacherId());
		
		return course;
		
	}
}
