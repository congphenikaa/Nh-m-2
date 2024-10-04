package javafx;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;

import application.Student;

public class StudentManagementUI extends Application {

    private TableView<Student> table;
    private ObservableList<Student> studentList;

    @Override
    public void start(Stage primaryStage) {
        // Tạo bảng và các cột
        table = new TableView<>();

        // Cột Student ID (kiểu String)
        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));

        // Cột Full Name (kiểu String)
        TableColumn<Student, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullName()));

        // Cột Date of Birth (kiểu LocalDate)
        TableColumn<Student, LocalDate> dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setMinWidth(150);
        dobColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateOfBirth()));

        // Cột Place of Birth (kiểu String)
        TableColumn<Student, String> placeColumn = new TableColumn<>("Place of Birth");
        placeColumn.setMinWidth(150);
        placeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaceOfBirth()));

        table.getColumns().addAll(idColumn, nameColumn, dobColumn, placeColumn);

        // Danh sách sinh viên ban đầu
        studentList = FXCollections.observableArrayList(
                new Student("001", "John Doe", LocalDate.of(2000, 1, 15), "New York"),
                new Student("002", "Jane Smith", LocalDate.of(1999, 5, 21), "Los Angeles")
        );
        table.setItems(studentList);

        // Các trường nhập liệu
        TextField idInput = new TextField();
        idInput.setPromptText("Student ID");

        TextField nameInput = new TextField();
        nameInput.setPromptText("Full Name");

        DatePicker dobInput = new DatePicker();
        dobInput.setPromptText("Date of Birth");

        TextField placeInput = new TextField();
        placeInput.setPromptText("Place of Birth");

        // Các nút thêm, sửa, xóa
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        // Thêm kiểu CSS cho các nút
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        editButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");

        // HBox chứa các trường nhập liệu
        HBox inputFields = new HBox(10);
        inputFields.getChildren().addAll(idInput, nameInput, dobInput, placeInput);
        inputFields.setPadding(new Insets(10));
        inputFields.setStyle("-fx-alignment: center;");

        // HBox chứa các nút
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setStyle("-fx-alignment: center;");

        // Bố cục chính
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(table, inputFields, buttonBox);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #f0f0f0;");

        // Tạo scene
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
