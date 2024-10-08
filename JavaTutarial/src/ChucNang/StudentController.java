package ChucNang;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.Student;

public class StudentController {
    private List<Student> students;

    // Constructor
    public StudentController() {
        this.students = new ArrayList<>();
    }

    // Thêm sinh viên
    public void addStudent(String studentId, String fullName, LocalDate dateOfBirth, String placeOfBirth) {
        Student newStudent = new Student(studentId, fullName, dateOfBirth, placeOfBirth);
        students.add(newStudent);
        System.out.println("Đã thêm sinh viên: " + fullName);
    }

    // Sửa thông tin sinh viên
    public void updateStudent(String studentId, String fullName, LocalDate dateOfBirth, String placeOfBirth) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.setFullName(fullName);
                student.setDateOfBirth(dateOfBirth);
                student.setPlaceOfBirth(placeOfBirth);
                System.out.println("Thông tin sinh viên với ID " + studentId + " đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên với ID " + studentId);
    }

    // Xóa sinh viên
    public void deleteStudent(String studentId) {
        students.removeIf(student -> student.getStudentId().equals(studentId));
        System.out.println("Đã xóa sinh viên với ID " + studentId);
    }

    // Tìm kiếm sinh viên theo ID
    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        System.out.println("Không tìm thấy sinh viên với ID " + studentId);
        return null;
    }

    // Liệt kê tất cả sinh viên
    public List<Student> listStudents() {
        return students;
    }
}
