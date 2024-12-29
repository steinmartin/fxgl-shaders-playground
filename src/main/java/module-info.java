module org.example.fxglshadersplayground {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens org.example.fxglshadersplayground to javafx.fxml;
    exports org.example.fxglshadersplayground;
}