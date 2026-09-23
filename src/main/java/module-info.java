module co.edu.uniquindio.poo.parcial {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.parcial to javafx.fxml;
    exports co.edu.uniquindio.poo.parcial;
}