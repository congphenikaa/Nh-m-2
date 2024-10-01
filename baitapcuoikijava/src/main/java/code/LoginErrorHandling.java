package code;

public class LoginErrorHandling {

    // Method to validate the login
    public void validateLogin(String username, String password) throws Exception {
        if (username == null || username.isEmpty()) {
            throw new Exception("Username cannot be empty!");  // Custom checked exception
        }
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("Password cannot be empty!");  // Custom unchecked exception
        }
        // Further validation logic can be added here
    }
}
