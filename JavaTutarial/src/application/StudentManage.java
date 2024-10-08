package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Class StudentManage để quản lý Student và Course
public class StudentManage {
	private List<Student> students;
	private List<Course> courses;

	// Constructor
	public StudentManage() {
		students = new ArrayList<>();
		courses = new ArrayList<>();
	}

	// Thêm sinh viên
	public void addStudent(Student student) {
		try {
			students.add(student);
			System.out.println("Student added successfully.");
		} catch (Exception e) {
			System.out.println("Error adding student: " + e.getMessage());
		}
	}

	// Sửa thông tin sinh viên theo StudentID
	public void updateStudent(String studentId, String fullName, LocalDate dateOfBirth, String placeOfBirth) {
		try {
			Student student = findStudentById(studentId);
			if (student != null) {
				student.setFullName(fullName);
				student.setDateOfBirth(dateOfBirth);
				student.setPlaceOfBirth(placeOfBirth);
				System.out.println("Student updated successfully.");
			} else {
				System.out.println("Student not found.");
			}
		} catch (Exception e) {
			System.out.println("Error updating student: " + e.getMessage());
		}
	}

	// Xóa sinh viên theo StudentID
	public void removeStudent(String studentId) {
		try {
			Student student = findStudentById(studentId);
			if (student != null) {
				students.remove(student);
				System.out.println("Student removed successfully.");
			} else {
				System.out.println("Student not found.");
			}
		} catch (Exception e) {
			System.out.println("Error removing student: " + e.getMessage());
		}
	}

	// Tìm sinh viên theo StudentID
	private Student findStudentById(String studentId) {
		for (Student student : students) {
			if (student.getStudentId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}

	// Thêm khóa học
	public void addCourse(Course course) {
		try {
			courses.add(course);
			System.out.println("Course added successfully.");
		} catch (Exception e) {
			System.out.println("Error adding course: " + e.getMessage());
		}
	}

	// Sửa thông tin khóa học theo CourseID
	public void updateCourse(String courseId, String courseName, int creditScores) {
		try {
			Course course = findCourseById(courseId);
			if (course != null) {
				course.setCourseName(courseName);
				course.setCreditScores(creditScores);
				System.out.println("Course updated successfully.");
			} else {
				System.out.println("Course not found.");
			}
		} catch (Exception e) {
			System.out.println("Error updating course: " + e.getMessage());
		}
	}

	// Xóa khóa học theo CourseID
	public void removeCourse(String courseId) {
		try {
			Course course = findCourseById(courseId);
			if (course != null) {
				courses.remove(course);
				System.out.println("Course removed successfully.");
			} else {
				System.out.println("Course not found.");
			}
		} catch (Exception e) {
			System.out.println("Error removing course: " + e.getMessage());
		}
	}

	// Tìm khóa học theo CourseID
	private Course findCourseById(String courseId) {
		for (Course course : courses) {
			if (course.getCourseID().equals(courseId)) {
				return course;
			}
		}
		return null;
	}
}
