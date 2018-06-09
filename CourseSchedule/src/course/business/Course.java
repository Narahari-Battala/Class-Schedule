package course.business;

public class Course {

	
	private int courseId;
	private int courseNumber;
	private String courseName;
	
	
	public Course(int courseId, int courseNumber, String courseName) {
		super();
		this.courseId = courseId;
		this.courseNumber = courseNumber;
		this.courseName = courseName;
	}
	
	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
