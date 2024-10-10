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
import java.io.*;
import java.util.List;

public class StudentManager extends Application {
    private TableView<Student> table;
    private TextField idInput, nameInput, placeOfBirthInput;
    private DatePicker dateOfBirthInput;
    private ObservableList<Student> students;
	private Stage primaryStage;
    
	// Constructor to accept the primary stage
    public StudentManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        students = FXCollections.observableArrayList();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
 // Method to show the Student Manager page
    public void show() {
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
        table.setItems(students);

        // Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addStudent());

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editStudent());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteStudent());
        

        HBox buttonLayout = new HBox(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonLayout.getChildren().addAll(addButton, editButton, deleteButton);

        HBox inputLayout = new HBox(10);
        inputLayout.setPadding(new Insets(10, 10, 10, 10));
        inputLayout.getChildren().addAll(idInput, nameInput, dateOfBirthInput, placeOfBirthInput);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(table, inputLayout, buttonLayout);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadStudentsFromFile(); // Load existing students
    }

 // Add student method with date format validation
    private void addStudent() {
        try {
            long id = Long.parseLong(idInput.getText());
            String name = nameInput.getText();
            LocalDate dob = dateOfBirthInput.getValue();
            String place = placeOfBirthInput.getText();

            if (name.isEmpty() || dob == null || place.isEmpty()) {
                showAlert("Please fill in all fields with the correct format.");
                return;
            }

            Student student = new Student(id, name, dob, place);
            students.add(student);

            // Save to file after adding
            saveStudentsToFile();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Student ID format. Please enter a valid number.");
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

            // Save to file after editing
            saveStudentsToFile();
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

        // Save to file after deletion
        saveStudentsToFile();
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
    
    private static final String FILE_PATH = "students.csv";

    // Method to save students to a CSV file
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

    // Method to load students from a CSV file
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
            // No file yet, ignore
        } catch (IOException e) {
            showAlert("Error loading students from file.");
        }
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
