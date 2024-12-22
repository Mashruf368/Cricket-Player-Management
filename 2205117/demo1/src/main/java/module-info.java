module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.demo1;
    opens com.example.demo1 to javafx.fxml;  // Ensure the package is open to javafx.fxml
}



