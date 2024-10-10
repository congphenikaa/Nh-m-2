package javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LoginRegisterApp extends Application {

    private Map<String, String> users = new HashMap<>();  // Lưu thông tin đăng ký

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Khởi tạo giao diện ban đầu là trang đăng nhập
        primaryStage.setTitle("Login/Register");

        // Hiển thị giao diện đăng nhập ban đầu
        showLoginPage(primaryStage);
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

    // Xử lý đăng nhập
    private void handleLogin(String username, String password, Stage stage) {
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Login Error", "Please fill in all fields.");
        } else if (users.containsKey(username) && users.get(username).equals(password)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " + username + "!");
            // Chuyển sang trang chào mừng sau khi đăng nhập thành công
            showWelcomePage(stage, username);
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid username or password.");
        }
    }

    // Xử lý đăng ký
    private void handleRegister(String username, String password, String confirmPassword, Stage stage) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Registration Error", "Please fill in all fields.");
        } else if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Registration Error", "Passwords do not match.");
        } else if (users.containsKey(username)) {
            showAlert(Alert.AlertType.ERROR, "Registration Error", "Username already exists.");
        } else {
            // Đăng ký thành công
            users.put(username, password);
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "You can now log in with your credentials.");
            // Chuyển về trang đăng nhập sau khi đăng ký
            showLoginPage(stage);
        }
    }

    // Trang chào mừng sau khi đăng nhập thành công
    private void showWelcomePage(Stage stage, String username) {
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        VBox welcomeLayout = new VBox(10);
        welcomeLayout.setPadding(new Insets(20));
        welcomeLayout.getChildren().add(welcomeLabel);

        Scene welcomeScene = new Scene(welcomeLayout, 300, 250);
        stage.setScene(welcomeScene);
        stage.show();
    }

    // Hiển thị thông báo lỗi hoặc thông báo thành công
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
