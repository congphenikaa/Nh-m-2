package KhoiDong;

import javafx.LoginPage; // Import LoginPage
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Login/Register");
		LoginPage loginPage = new LoginPage(primaryStage); // Khởi tạo trang đăng nhập
		loginPage.show(); // Hiển thị trang đăng nhập đầu tiên
	}

	public static void main(String[] args) {
		launch(args);
	}
}
