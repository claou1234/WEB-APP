package gr.aueb.sev.dto;

public class StudentCourseDTO {
	private int studentId;
	private int courseId;
	
	public StudentCourseDTO() {
		
	}

	public StudentCourseDTO(int studentId, int courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}


	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	

}
