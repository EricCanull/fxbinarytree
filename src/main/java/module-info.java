module com.ericcanull.fxbinarytree {
    requires javafx.controls;
    requires javafx.fxml;

    // Export the package that has your controller classes to JavaFX
    exports com.ericcanull.fxbinarytree.controller to javafx.fxml;

    // Open the package that has your Main and controller classes
    opens com.ericcanull.fxbinarytree.app;
    opens com.ericcanull.fxbinarytree.controller;

    exports com.ericcanull.fxbinarytree.app to javafx.graphics;
}