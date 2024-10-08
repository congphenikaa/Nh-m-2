package javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import application.Student;

public class StudentManager extends Application {
    private TableView<Student> table;
    private TextField idInput, nameInput, placeOfBirthInput;
    private DatePicker dateOfBirthInput;
    private ObservableList<Student> students;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management");

        // Student ID input
        idInput = new TextField();
        idInput.setPromptText("Student ID");
        
        // Full Name input
        nameInput = new TextField();
        nameInput.setPromptText("Full Name");
        
        // Date of Birth input
        dateOfBirthInput = new DatePicker();
        dateOfBirthInput.setPromptText("Date of Birth");
        
        // Place of Birth input
        placeOfBirthInput = new TextField();
        placeOfBirthInput.setPromptText("Place of Birth");

        // TableView columns
        TableColumn<Student, Long> idColumn = new TableColumn<>("Student ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStudentId()));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFullName()));

        TableColumn<Student, LocalDate> dateOfBirthColumn = new TableColumn<>("Date of Birth");
        dateOfBirthColumn.setMinWidth(120);
        dateOfBirthColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDateOfBirth()));

        TableColumn<Student, String> placeOfBirthColumn = new TableColumn<>("Place of Birth");
        placeOfBirthColumn.setMinWidth(150);
        placeOfBirthColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPlaceOfBirth()));

        // Table setup
        table = new TableView<>();
        table.getColumns().addAll(idColumn, nameColumn, dateOfBirthColumn, placeOfBirthColumn);
        
        // Observable list for students
        students = FXCollections.observableArrayList();
        table.setItems(students);

        // Add button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addStudent());

        // Edit button
        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editStudent());

        // Delete button
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteStudent());

        // Layout for buttons
        HBox buttonLayout = new HBox(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonLayout.getChildren().addAll(addButton, editButton, deleteButton);

        // Layout for inputs
        HBox inputLayout = new HBox(10);
        inputLayout.setPadding(new Insets(10, 10, 10, 10));
        inputLayout.getChildren().addAll(idInput, nameInput, dateOfBirthInput, placeOfBirthInput);

        // Main layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(table, inputLayout, buttonLayout);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

 // Add student method with date format validation
    private void addStudent() {
        try {
            long id = Long.parseLong(idInput.getText());
            String name = nameInput.getText();
            LocalDate dob = dateOfBirthInput.getValue(); // Lấy giá trị trực tiếp từ DatePicker
            String place = placeOfBirthInput.getText();

            if (name.isEmpty() || dob == null || place.isEmpty()) {
                showAlert("Please fill in all fields with the correct format.");
                return;
            }

            Student student = new Student(id, name, dob, place);
            students.add(student);
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Student ID format. Please enter a valid number.");
        } catch (DateTimeParseException e) {
            showAlert("Invalid Date of Birth format. Please enter the date in dd/MM/yyyy format.");
        }
    }


    // Validate date format
    private LocalDate validateDateFormat(String dateInput) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateInput, formatter);
    }

    // Edit selected student
    private void editStudent() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showAlert("Please select a student to edit.");
            return;
        }

        try {
            long id = Long.parseLong(idInput.getText());
            selectedStudent.setStudentId(id);
            selectedStudent.setFullName(nameInput.getText());
            selectedStudent.setDateOfBirth(dateOfBirthInput.getValue());
            selectedStudent.setPlaceOfBirth(placeOfBirthInput.getText());
            table.refresh();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Student ID format. Please enter a valid number.");
        }
    }

    // Delete selected student
    private void deleteStudent() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showAlert("Please select a student to delete.");
            return;
        }

        students.remove(selectedStudent);
        clearFields();
    }

    // Clear input fields
    private void clearFields() {
        idInput.clear();
        nameInput.clear();
        dateOfBirthInput.setValue(null);
        placeOfBirthInput.clear();
    }

    // Show alert dialog
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
