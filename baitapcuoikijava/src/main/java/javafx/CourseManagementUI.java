package javafx;

import application.Course;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CourseManagementUI extends Application {

    private TableView<Course> table;
    private ObservableList<Course> courseList;

    @Override
    public void start(Stage primaryStage) {
        // Tạo bảng và các cột
        table = new TableView<>();

        TableColumn<Course, String> idColumn = new TableColumn<>("Course ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseID()));

        TableColumn<Course, String> nameColumn = new TableColumn<>("Course Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseName()));

        TableColumn<Course, Integer> creditsColumn = new TableColumn<>("Credit Scores");
        creditsColumn.setMinWidth(100);
        creditsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCreditScores()).asObject());

        table.getColumns().addAll(idColumn, nameColumn, creditsColumn);

        // Danh sách khóa học ban đầu
        courseList = FXCollections.observableArrayList(
                new Course("C001", "Mathematics", 3),
                new Course("C002", "Physics", 4)
        );
        table.setItems(courseList);

        // Các trường nhập liệu
        Label idLabel = new Label("Course ID:");
        idLabel.setFont(new Font("Arial", 14));
        TextField idInput = new TextField();
        idInput.setPromptText("Enter course ID");

        Label nameLabel = new Label("Course Name:");
        nameLabel.setFont(new Font("Arial", 14));
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter course name");

        Label creditsLabel = new Label("Credit Scores:");
        creditsLabel.setFont(new Font("Arial", 14));
        TextField creditsInput = new TextField();
        creditsInput.setPromptText("Enter credit scores");

        // Các nút thêm, sửa, xóa với style
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        addButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        editButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");

        addButton.setMinWidth(80);
        editButton.setMinWidth(80);
        deleteButton.setMinWidth(80);

        // Hành động khi nhấn nút thêm
        addButton.setOnAction(e -> {
            String courseID = idInput.getText();
            String courseName = nameInput.getText();
            int creditScores = Integer.parseInt(creditsInput.getText());

            Course newCourse = new Course(courseID, courseName, creditScores);
            courseList.add(newCourse);

            idInput.clear();
            nameInput.clear();
            creditsInput.clear();
        });

        // Hành động khi nhấn nút sửa
        editButton.setOnAction(e -> {
            Course selectedCourse = table.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                selectedCourse.setCourseID(idInput.getText());
                selectedCourse.setCourseName(nameInput.getText());
                selectedCourse.setCreditScores(Integer.parseInt(creditsInput.getText()));
                table.refresh(); // Làm mới bảng sau khi chỉnh sửa
            }
        });

        // Hành động khi nhấn nút xóa
        deleteButton.setOnAction(e -> {
            Course selectedCourse = table.getSelectionModel().getSelectedItem();
            courseList.remove(selectedCourse);
        });

        // Bố cục cho các trường nhập liệu
        GridPane inputFields = new GridPane();
        inputFields.setPadding(new Insets(10));
        inputFields.setHgap(10);
        inputFields.setVgap(10);

        inputFields.add(idLabel, 0, 0);
        inputFields.add(idInput, 1, 0);
        inputFields.add(nameLabel, 0, 1);
        inputFields.add(nameInput, 1, 1);
        inputFields.add(creditsLabel, 0, 2);
        inputFields.add(creditsInput, 1, 2);

        // Bố cục cho các nút
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        // Bố cục chính
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(table, inputFields, buttonBox);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #f8f9fa;");

        // Tạo scene
        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Course Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
