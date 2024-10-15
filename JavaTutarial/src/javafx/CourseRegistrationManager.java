package javafx;

import application.Course;
import application.Student;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationManager extends Application {
    private TableView<Student> studentTable;
    private TableView<Course> courseTable, registeredCourseTable;
    private ObservableList<Student> studentList;
    private ObservableList<Course> courseList, registeredCourses;
    private static final String STUDENT_FILE = "students.csv";
    private static final String COURSE_FILE = "courses.csv";
    private static final String REGISTRATION_FILE = "registrations.csv";  // File lưu đăng ký
    private Scene previousScene;  // Store the previous scene for back navigation

    // Constructor to accept the previous scene
    public CourseRegistrationManager(Scene previousScene) {
        this.previousScene = previousScene;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quản lý Đăng ký Môn học");

        // Khởi tạo danh sách dữ liệu
        studentList = FXCollections.observableArrayList();
        courseList = FXCollections.observableArrayList();
        registeredCourses = FXCollections.observableArrayList();

        // Tải dữ liệu sinh viên và môn học từ file
        loadStudents();
        loadCourses();

        // Cấu hình bảng sinh viên
        studentTable = new TableView<>(studentList);
        TableColumn<Student, Long> studentIdCol = new TableColumn<>("Mã SV");
        studentIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStudentId()));
        TableColumn<Student, String> studentNameCol = new TableColumn<>("Họ và Tên");
        studentNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFullName()));
        studentTable.getColumns().addAll(studentIdCol, studentNameCol);
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) loadRegisteredCourses(newSel);  // Tải các môn học đã đăng ký của sinh viên
        });

        // Cấu hình bảng môn học
        courseTable = new TableView<>(courseList);
        TableColumn<Course, String> courseIdCol = new TableColumn<>("Mã MH");
        courseIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourseID()));
        TableColumn<Course, String> courseNameCol = new TableColumn<>("Tên Môn Học");
        courseNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourseName()));
        courseTable.getColumns().addAll(courseIdCol, courseNameCol);

        // Cấu hình bảng môn học đã đăng ký
        registeredCourseTable = new TableView<>(registeredCourses);
        TableColumn<Course, String> regCourseIdCol = new TableColumn<>("Mã MH");
        regCourseIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourseID()));
        TableColumn<Course, String> regCourseNameCol = new TableColumn<>("Tên Môn Học");
        regCourseNameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCourseName()));
        registeredCourseTable.getColumns().addAll(regCourseIdCol, regCourseNameCol);

        // Các nút chức năng
        Button registerButton = new Button("Đăng ký");
        registerButton.setOnAction(e -> registerCourse());

        Button removeRegistrationButton = new Button("Hủy đăng ký");
        removeRegistrationButton.setOnAction(e -> removeRegistration());

        // Nút quay lại
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(previousScene)); // Return to the previous scene

        // Bố cục giao diện
        HBox tableLayout = new HBox(20);
        tableLayout.getChildren().addAll(studentTable, courseTable, registeredCourseTable);

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(registerButton, removeRegistrationButton, backButton);

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(15));
        mainLayout.getChildren().addAll(tableLayout, buttonLayout);

        // Cài đặt Scene
        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Tải danh sách sinh viên từ file students.csv
    private void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                long id = Long.parseLong(fields[0]);
                String name = fields[1];
                LocalDate dob = LocalDate.parse(fields[2], formatter);
                String placeOfBirth = fields[3];
                Student student = new Student(id, name, dob, placeOfBirth);
                studentList.add(student);
            }
        } catch (FileNotFoundException e) {
            showAlert("Không tìm thấy file sinh viên.");
        } catch (IOException e) {
            showAlert("Lỗi đọc file sinh viên.");
        } catch (NumberFormatException e) {
            showAlert("Dữ liệu không hợp lệ trong file sinh viên.");
        }
    }

    // Tải danh sách môn học từ file courses.csv
    private void loadCourses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String courseID = fields[0];
                String courseName = fields[1];
                int creditScores = Integer.parseInt(fields[2]);
                Course course = new Course(courseID, courseName, creditScores);
                courseList.add(course);
            }
        } catch (FileNotFoundException e) {
            showAlert("Không tìm thấy file môn học.");
        } catch (IOException e) {
            showAlert("Lỗi đọc file môn học.");
        } catch (NumberFormatException e) {
            showAlert("Dữ liệu không hợp lệ trong file môn học.");
        }
    }

    // Đăng ký môn học cho sinh viên
    private void registerCourse() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();

        if (selectedStudent == null || selectedCourse == null) {
            showAlert("Vui lòng chọn sinh viên và môn học.");
            return;
        }

        // Kiểm tra xem môn học đã được đăng ký chưa
        if (registeredCourses.contains(selectedCourse)) {
            showAlert("Sinh viên đã đăng ký môn học này.");
            return;
        }

        // Thêm môn học vào danh sách đã đăng ký và lưu lại
        registeredCourses.add(selectedCourse);
        saveRegistration(selectedStudent, registeredCourses);
    }

    // Hủy đăng ký môn học cho sinh viên
    private void removeRegistration() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        Course selectedCourse = registeredCourseTable.getSelectionModel().getSelectedItem();

        if (selectedStudent == null || selectedCourse == null) {
            showAlert("Vui lòng chọn sinh viên và môn học để hủy.");
            return;
        }

        // Xóa môn học khỏi danh sách đã đăng ký và lưu lại
        registeredCourses.remove(selectedCourse);
        saveRegistration(selectedStudent, registeredCourses);
    }

    // Tải danh sách môn học đã đăng ký của sinh viên
    private void loadRegisteredCourses(Student student) {
        registeredCourses.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(REGISTRATION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                long studentId = Long.parseLong(fields[0]);
                if (student.getStudentId() == studentId) {
                    String courseId = fields[1];
                    String courseName = fields[2];
                    int creditScores = Integer.parseInt(fields[3]);
                    Course course = new Course(courseId, courseName, creditScores);
                    registeredCourses.add(course);
                }
            }
        } catch (FileNotFoundException e) {
            // File chưa tồn tại, bỏ qua
        } catch (IOException e) {
            showAlert("Lỗi tải danh sách đăng ký.");
        }
    }

    // Lưu đăng ký môn học của sinh viên
    private void saveRegistration(Student student, ObservableList<Course> courses) {
        // Đọc tất cả các đăng ký hiện có
        List<String> allRegistrations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REGISTRATION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allRegistrations.add(line);
            }
        } catch (FileNotFoundException e) {
            // File chưa tồn tại, sẽ tạo mới
        } catch (IOException e) {
            showAlert("Lỗi đọc file đăng ký.");
            return;
        }

        // Xóa các đăng ký cũ của sinh viên
        allRegistrations.removeIf(s -> s.startsWith(String.valueOf(student.getStudentId()) + ","));

        // Thêm các đăng ký mới của sinh viên
        for (Course course : courses) {
            String registrationLine = student.getStudentId() + "," + course.getCourseID() + "," + course.getCourseName() + "," + course.getCreditScores();
            allRegistrations.add(registrationLine);
        }

        // Ghi tất cả các đăng ký trở lại vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REGISTRATION_FILE))) {
            for (String regLine : allRegistrations) {
                writer.write(regLine);
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert("Lỗi lưu đăng ký.");
        }
    }

    // Hiển thị thông báo
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
