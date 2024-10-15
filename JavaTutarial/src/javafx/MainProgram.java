package javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainProgram extends Application {

    private Stage primaryStage; // Store the primary stage for navigation
    private Scene mainMenuScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Initialize primary stage
        primaryStage.setTitle("Student Management");

        // Title for the main page
        Text title = new Text("Student Management");
        title.setFont(Font.font("Arial", 36));
        title.setFill(Color.DARKBLUE);

        // Buttons for different functionalities
        Button studentManagementButton = createStyledButton("Student Management");
        Button courseManagementButton = createStyledButton("Course Management");
        Button courseRegistrationButton = createStyledButton("Course Registration");
        Button gradeManagementButton = createStyledButton("Enter/Update Grades");
        Button logoutButton = createStyledButton("Logout"); // Logout button

        // Adding action for each button
        studentManagementButton.setOnAction(e -> openStudentManager());  // Navigate to Student Management
        courseManagementButton.setOnAction(e -> openCourseManagement());
        courseRegistrationButton.setOnAction(e -> openCourseRegistration());
        gradeManagementButton.setOnAction(e -> openGradeManagement());
        logoutButton.setOnAction(e -> logout()); // Action for logout
        

        // Button layout
        VBox buttonLayout = new VBox(15); // Spacing between buttons
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(studentManagementButton, courseManagementButton,
                courseRegistrationButton, gradeManagementButton, logoutButton);

        // Main layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(buttonLayout);
        borderPane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER); // Center the title at the top

        // Padding and background color
        borderPane.setPadding(new Insets(20));
        borderPane.setStyle("-fx-background-color: #F0F8FF;"); // Light blue background

        // Setting the scene
        Scene scene = new Scene(borderPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    // Create a styled button
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(200);
        button.setFont(Font.font("Arial", 16));
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10; -fx-font-weight: bold; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #3E8E41; -fx-border-width: 2;");

        // Add hover effect
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-padding: 10; -fx-font-weight: bold;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10; -fx-font-weight: bold;"));

        return button;
    }

    // Navigate to Student Management
    private void openStudentManager() {
        StudentManager studentManager = new StudentManager();
        try {
            studentManager.start(primaryStage, primaryStage.getScene()); // Pass the main scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void openCourseManagement() {
        CourseManager courseManager = new CourseManager(primaryStage, primaryStage.getScene()); // Pass the main scene
        try {
            courseManager.start(primaryStage); // Navigate to Course Management
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openCourseRegistration() {

        // Pass the current scene to CourseRegistrationManager
        CourseRegistrationManager courseRegistrationManager = new CourseRegistrationManager(primaryStage.getScene());

        try {
            // Launch the Course Registration page
            courseRegistrationManager.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void openGradeManagement() {
        System.out.println("Navigating to Grade Management...");
        // Add code to navigate to the Grade Management screen
    }

    // Logout method to return to the login page
    private void logout() {
        System.out.println("Logging out...");
        LoginPage loginPage = new LoginPage(primaryStage); // Create a new LoginPage
        loginPage.show(); // Show the login page
    }

    public static void main(String[] args) {
        launch(args);
    }
}
