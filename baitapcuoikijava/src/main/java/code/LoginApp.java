package code;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System - Login");

        // Creating a grid pane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Adding a welcome text
        Text welcomeText = new Text("Welcome to Student Management");
        welcomeText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(welcomeText, 0, 0, 2, 1);

        // Username label and field
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);

        TextField usernameField = new TextField();
        grid.add(usernameField, 1, 1);

        // Password label and field
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        // Login button
        Button loginBtn = new Button("Login");
        grid.add(loginBtn, 1, 4);

        // Styling elements
        loginBtn.setStyle("-fx-background-color: #5DADE2; -fx-text-fill: white;");
        welcomeText.setFill(Color.DARKBLUE);

        // Handling login button click event
        loginBtn.setOnAction(e -> {
            LoginErrorHandling errorHandling = new LoginErrorHandling();
            try {
                // Attempt to validate login
                errorHandling.validateLogin(usernameField.getText(), passwordField.getText());
                System.out.println("Login Successful!");
            } catch (Exception ex) {
                // Catch any Exception or RuntimeException thrown by LoginErrorHandling
                System.out.println("Login Failed: " + ex.getMessage());
            } catch (RuntimeException ex) {
                System.out.println("Runtime Error: " + ex.getMessage());
            }
        });

        // Adding scene and showing stage
        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
