package gr.aueb.sev.dto;

public class CourseDTO {
	private int id;
	private String description;
	private int teacherId;
	
	public CourseDTO() {
		
	}

	public CourseDTO(int id, String description, int teacherId) {
		this.id = id;
		this.description = description;
		this.teacherId = teacherId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	
	

}
