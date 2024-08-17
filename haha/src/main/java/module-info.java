module com.example.haha {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.haha to javafx.fxml;
    exports com.example.haha;
}