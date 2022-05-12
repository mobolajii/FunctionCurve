module com.example.lernen {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lernen to javafx.fxml;
    exports com.example.lernen;
}