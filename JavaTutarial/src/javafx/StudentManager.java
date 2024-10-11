package javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.io.*;

import application.Student;

public class StudentManager extends Application {
    private TableView<Student> table;
    private TextField idInput, nameInput, placeOfBirthInput;
    private DatePicker dateOfBirthInput;
    private ObservableList<Student> students;

    public StudentManager() {
        students = FXCollections.observableArrayList();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        start(primaryStage, null);  // Call the overloaded method without a previous scene
    }

    // Overloaded start method that accepts the previous scene
    public void start(Stage primaryStage, Scene previousScene) {
        primaryStage.setTitle("Student Management");

        // Student input fields
        idInput = new TextField();
        idInput.setPromptText("Student ID");
        nameInput = new TextField();
        nameInput.setPromptText("Full Name");
        dateOfBirthInput = new DatePicker();
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
        table.setItems(students);

        // Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addStudent());

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editStudent());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteStudent());

        // "Back" button to return to the previous scene
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(previousScene));  // Switch back to the previous scene

        // Layouts
        HBox inputLayout = new HBox(10);
        inputLayout.setPadding(new Insets(10, 10, 10, 10));
        inputLayout.getChildren().addAll(idInput, nameInput, dateOfBirthInput, placeOfBirthInput);

        HBox buttonLayout = new HBox(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonLayout.getChildren().addAll(addButton, editButton, deleteButton, backButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(table, inputLayout, buttonLayout);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadStudentsFromFile();  // Load students when the scene opens
    }

    // Add student method
    private void addStudent() {
        try {
            long id = Long.parseLong(idInput.getText());
            String name = nameInput.getText();
            LocalDate dob = dateOfBirthInput.getValue();
            String place = placeOfBirthInput.getText();

            if (name.isEmpty() || dob == null || place.isEmpty()) {
                showAlert("Please fill in all fields.");
                return;
            }

            Student student = new Student(id, name, dob, place);
            students.add(student);
            saveStudentsToFile();  // Save changes
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Student ID format.");
        }
    }

    // Edit student method
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
            saveStudentsToFile();  // Save changes
            table.refresh();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Student ID format.");
        }
    }

    // Delete student method
    private void deleteStudent() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showAlert("Please select a student to delete.");
            return;
        }

        students.remove(selectedStudent);
        saveStudentsToFile();  // Save changes
        clearFields();
    }

    // Clear input fields
    private void clearFields() {
        idInput.clear();
        nameInput.clear();
        dateOfBirthInput.setValue(null);
        placeOfBirthInput.clear();
    }

    // Show an alert
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // File handling (same as your original code)
    private static final String FILE_PATH = "students.csv";

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.getStudentId() + "," +
                             student.getFullName() + "," +
                             student.getDateOfBirth() + "," +
                             student.getPlaceOfBirth());
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert("Error saving students to file.");
        }
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                long id = Long.parseLong(fields[0]);
                String name = fields[1];
                LocalDate dob = LocalDate.parse(fields[2]);
                String place = fields[3];
                Student student = new Student(id, name, dob, place);
                students.add(student);
            }
        } catch (FileNotFoundException e) {
            // File not found, no students to load
        } catch (IOException e) {
            showAlert("Error loading students from file.");
        }
    }
}
