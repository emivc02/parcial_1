module co.edu.uniquindio.poo.parcial {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.parcial to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial.controller to javafx.fxml;
    opens model to javafx.base;
    
    exports co.edu.uniquindio.poo.parcial;
    exports co.edu.uniquindio.poo.parcial.controller;
}