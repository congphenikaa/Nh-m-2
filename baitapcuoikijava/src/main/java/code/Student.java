package code;

import java.time.LocalDate;

//Class Student
public class Student {
	 private String studentId;
	 private String fullName;
	 private LocalDate dateOfBirth;
	 private String placeOfBirth;
	
	 // Constructor
	 public Student(String studentId, String fullName, LocalDate dateOfBirth, String placeOfBirth) {
	     this.studentId = studentId;
	     this.fullName = fullName;
	     this.dateOfBirth = dateOfBirth;
	     this.placeOfBirth = placeOfBirth;
	 }	
	
	 // Getter và Setter cho các thuộc tính
	 public String getStudentId() {
	     return studentId;
	 }
	
	 public void setStudentId(String studentId) {
	     this.studentId = studentId;
	 }
	
	 public String getFullName() {
	     return fullName;
	 }
	
	 public void setFullName(String fullName) {
	     this.fullName = fullName;
	 }
	
	 public LocalDate getDateOfBirth() {
	     return dateOfBirth;
	 }
	
	 public void setDateOfBirth(LocalDate dateOfBirth) {
	     this.dateOfBirth = dateOfBirth;
	 }
	
	 public String getPlaceOfBirth() {
	     return placeOfBirth;
	 }
	
	 public void setPlaceOfBirth(String placeOfBirth) {
	     this.placeOfBirth = placeOfBirth;
	 }
}
