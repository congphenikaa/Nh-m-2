package javafx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LoginPage {

    private Map<String, String> users = new HashMap<>();  // Store registered user information
    private Stage stage;

    public LoginPage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Display the login interface
        showLoginPage(stage);
    }

    private void showLoginPage(Stage stage) {
        // Title
        Label titleLabel = new Label("Login Page");

        // Username input
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Username");

        // Password input
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin(usernameInput.getText(), passwordInput.getText(), stage));

        // Switch to register button
        Button switchToRegisterButton = new Button("Go to Register");
        switchToRegisterButton.setOnAction(e -> showRegisterPage(stage));

        // Layout for login form
        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new Insets(20));
        loginLayout.getChildren().addAll(titleLabel, usernameInput, passwordInput, loginButton, switchToRegisterButton);

        Scene loginScene = new Scene(loginLayout, 300, 250);
        stage.setScene(loginScene);
        stage.show();
    }

    private void showRegisterPage(Stage stage) {
        // Title
        Label titleLabel = new Label("Register Page");

        // Username input
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Username");

        // Password input
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");

        // Confirm password input
        PasswordField confirmPasswordInput = new PasswordField();
        confirmPasswordInput.setPromptText("Confirm Password");

        // Register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> handleRegister(usernameInput.getText(), passwordInput.getText(), confirmPasswordInput.getText(), stage));

        // Switch to login button
        Button switchToLoginButton = new Button("Go to Login");
        switchToLoginButton.setOnAction(e -> showLoginPage(stage));

        // Layout for register form
        VBox registerLayout = new VBox(10);
        registerLayout.setPadding(new Insets(20));
        registerLayout.getChildren().addAll(titleLabel, usernameInput, passwordInput, confirmPasswordInput, registerButton, switchToLoginButton);

        Scene registerScene = new Scene(registerLayout, 300, 250);
        stage.setScene(registerScene);
        stage.show();
    }

    // Handle login
    private void handleLogin(String username, String password, Stage stage) {
        try {
            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Login Error", "Please fill in all fields.");
            } else if (users.containsKey(username) && users.get(username).equals(password)) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " + username + "!");
                // Switch to the main program after a successful login
                goToMainProgram(stage);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid username or password.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    // Handle registration
    private void handleRegister(String username, String password, String confirmPassword, Stage stage) {
        try {
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "Please fill in all fields.");
            } else if (username.length() < 3) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "Username must be at least 3 characters long.");
            } else if (password.length() < 6) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "Password must be at least 6 characters long.");
            } else if (!password.equals(confirmPassword)) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "Passwords do not match.");
            } else if (users.containsKey(username)) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "Username already exists.");
            } else {
                // Successful registration
                users.put(username, password);
                showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "You can now log in with your credentials.");
                // Switch back to login page after registration
                showLoginPage(stage);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    // Switch to the main program after successful login
    private void goToMainProgram(Stage stage) {
        MainProgram mainProgram = new MainProgram();  // Create a MainProgram object
        mainProgram.start(stage);  // Call the start method of MainProgram
    }

    // Show error or success message
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
