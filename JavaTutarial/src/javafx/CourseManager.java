package javafx;

import application.Course;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class CourseManager extends Application {

	private TableView<Course> table;
	private ObservableList<Course> courseList;
	private TextField idInput, nameInput, creditsInput;
	private Scene previousScene; // Store the previous scene
	private Stage primaryStage; // Store the primary stage

	private static final String FILE_PATH = "courses.csv";

	public CourseManager(Stage primaryStage, Scene previousScene) {
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// You can still launch this directly for testing without passing the stage and
		// previous scene
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Course Management");

		// Initialize course list
		courseList = FXCollections.observableArrayList();

		// Load existing courses from the file at startup
		loadCoursesFromFile();

		// Create table and columns
		table = new TableView<>();

		TableColumn<Course, String> idColumn = new TableColumn<>("Course ID");
		idColumn.setMinWidth(100);
		idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseID()));

		TableColumn<Course, String> nameColumn = new TableColumn<>("Course Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseName()));

		TableColumn<Course, Integer> creditsColumn = new TableColumn<>("Credit Scores");
		creditsColumn.setMinWidth(100);
		creditsColumn.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getCreditScores()).asObject());

		table.getColumns().addAll(idColumn, nameColumn, creditsColumn);
		table.setItems(courseList); // Set the course list to the table

		// Input fields
		idInput = new TextField();
		idInput.setPromptText("Course ID");

		nameInput = new TextField();
		nameInput.setPromptText("Course Name");

		creditsInput = new TextField();
		creditsInput.setPromptText("Credit Scores");

		// Buttons
		Button addButton = new Button("Add");
		Button editButton = new Button("Edit");
		Button deleteButton = new Button("Delete");
		Button backButton = new Button("Back"); // New Back button

		addButton.setOnAction(e -> addCourse());
		editButton.setOnAction(e -> editCourse());
		deleteButton.setOnAction(e -> deleteCourse());
		backButton.setOnAction(e -> goBack()); // Go back to previous scene

		// Layout for input fields
		HBox inputLayout = new HBox(10);
		inputLayout.setPadding(new Insets(10));
		inputLayout.getChildren().addAll(new Label("Course ID:"), idInput, new Label("Course Name:"), nameInput,
				new Label("Credit Scores:"), creditsInput);

		// Layout for buttons
		HBox buttonLayout = new HBox(10);
		buttonLayout.setPadding(new Insets(10));
		buttonLayout.getChildren().addAll(addButton, editButton, deleteButton, backButton); // Add back button

		VBox mainLayout = new VBox(10);
		mainLayout.getChildren().addAll(table, inputLayout, buttonLayout);

		Scene scene = new Scene(mainLayout, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// Go back to the previous scene (Main Menu)
	private void goBack() {
		primaryStage.setScene(previousScene); // Set the scene to the previous scene
	}

	// Method to add a course
	private void addCourse() {
		String courseID = idInput.getText();
		String courseName = nameInput.getText();
		int creditScores;

		try {
			creditScores = Integer.parseInt(creditsInput.getText());
		} catch (NumberFormatException e) {
			showAlert("Invalid input", "Credit Scores must be an integer.");
			return;
		}

		// Check for duplicate course ID
		if (isCourseIDDuplicate(courseID)) {
			showAlert("Duplicate Course ID", "A course with this ID already exists.");
			return;
		}

		Course course = new Course(courseID, courseName, creditScores);
		courseList.add(course); // Add to the list

		// Save to file after adding
		saveCoursesToFile();
		clearFields();
	}

	// Method to edit selected course
	private void editCourse() {
		Course selectedCourse = table.getSelectionModel().getSelectedItem();
		if (selectedCourse != null) {
			String newCourseID = idInput.getText();
			String newCourseName = nameInput.getText();
			int newCreditScores;

			try {
				newCreditScores = Integer.parseInt(creditsInput.getText());
			} catch (NumberFormatException e) {
				showAlert("Invalid input", "Credit Scores must be an integer.");
				return;
			}

			// Check for duplicate course ID
			if (isCourseIDDuplicate(newCourseID, selectedCourse.getCourseID())) {
				showAlert("Duplicate Course ID", "A course with this ID already exists.");
				return;
			}

			courseList.remove(selectedCourse); // Remove the old course
			Course updatedCourse = new Course(newCourseID, newCourseName, newCreditScores);
			courseList.add(updatedCourse); // Add the updated course

			// Save to file after editing
			saveCoursesToFile();
			table.refresh(); // Refresh the table to show updated data
			clearFields();
		} else {
			showAlert("No course selected", "Please select a course to edit.");
		}
	}

	// Method to check if a course ID is a duplicate
	private boolean isCourseIDDuplicate(String courseID) {
		for (Course course : courseList) {
			if (course.getCourseID().equals(courseID)) {
				return true; // Duplicate found
			}
		}
		return false; // No duplicates
	}

	// Overloaded method to check for duplicates while editing
	private boolean isCourseIDDuplicate(String courseID, String currentCourseID) {
		for (Course course : courseList) {
			if (course.getCourseID().equals(courseID) && !course.getCourseID().equals(currentCourseID)) {
				return true; // Duplicate found
			}
		}
		return false; // No duplicates
	}

	// Method to delete selected course
	private void deleteCourse() {
		Course selectedCourse = table.getSelectionModel().getSelectedItem();
		if (selectedCourse != null) {
			courseList.remove(selectedCourse); // Remove the selected course

			// Save to file after deletion
			saveCoursesToFile();
			table.refresh(); // Refresh the table to reflect deletion
			clearFields();
		} else {
			showAlert("No course selected", "Please select a course to delete.");
		}
	}

	// Method to clear input fields
	private void clearFields() {
		idInput.clear();
		nameInput.clear();
		creditsInput.clear();
	}

	// Method to show alert
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}

	// Method to save courses to a CSV file
	private void saveCoursesToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (Course course : courseList) {
				writer.write(course.getCourseID() + "," + course.getCourseName() + "," + course.getCreditScores());
				writer.newLine();
			}
		} catch (IOException e) {
			showAlert("Error saving courses to file.", null);
		}
	}

	// Method to load courses from a CSV file
	private void loadCoursesFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				String courseID = fields[0];
				String courseName = fields[1];
				int creditScores = Integer.parseInt(fields[2]);

				Course course = new Course(courseID, courseName, creditScores);
				courseList.add(course); // Add course to the list
			}
		} catch (FileNotFoundException e) {
			// No file yet, ignore
		} catch (IOException e) {
			showAlert("Error loading courses from file.", null);
		}
	}
}
