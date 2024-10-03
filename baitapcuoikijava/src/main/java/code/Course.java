package code;

//Class Course
public class Course {
	private String courseID;
	private String courseName;
	private int creditScores;

	// Constructor
	public Course(String courseID, String courseName, int creditScores) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.creditScores = creditScores;
	}

	// Getter và Setter cho các thuộc tính
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCreditScores() {
		return creditScores;
	}

	public void setCreditScores(int creditScores) {
		this.creditScores = creditScores;
	}
}
