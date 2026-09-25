module co.edu.uniquindio.poo.parcial {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jshell;

    opens co.edu.uniquindio.poo.parcial to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial.controller to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial.model to javafx.base;
    
    exports co.edu.uniquindio.poo.parcial;
    exports co.edu.uniquindio.poo.parcial.controller;
}