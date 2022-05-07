module com.example.ccmindtest1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.ccmindtest1 to javafx.fxml;
    exports com.example.ccmindtest1;
}