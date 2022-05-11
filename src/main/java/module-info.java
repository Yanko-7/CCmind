module com.example.ccmindtest1 {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.swing;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.swing;
    requires com.jfoenix;

    opens com.example.ccmindtest1 to javafx.fxml;
    exports com.example.ccmindtest1;
}