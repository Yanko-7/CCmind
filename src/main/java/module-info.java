module com.example.ccmind {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires com.jfoenix;
    requires javafx.swing;
    opens com.example.ccmind to javafx.fxml;
    exports com.example.ccmind;
}