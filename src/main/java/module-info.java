module com.example.activity10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.activity10 to javafx.fxml;
    exports com.example.activity10;
}