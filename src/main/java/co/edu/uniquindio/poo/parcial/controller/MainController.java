package co.edu.uniquindio.poo.parcial.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import co.edu.uniquindio.poo.parcial.model.gimnasio;

/**
 * Controlador de la ventana principal.
 * Muestra los datos del gimnasio (Singleton) y refresca cada pestaña al seleccionarla,
 * para que los datos registrados en una pestaña aparezcan en las demás.
 */
public class MainController {

    @FXML private Label lblNombre;
    @FXML private Label lblDatos;
    @FXML private TabPane tabs;

    // Controladores de las vistas incluidas con fx:include (fx:id + "Controller")
    @FXML private ClientesController clientesController;
    @FXML private EntrenadoresController entrenadoresController;
    @FXML private PlanesController planesController;
    @FXML private ServiciosController serviciosController;
    @FXML private InscripcionesController inscripcionesController;
    @FXML private ConsultasController consultasController;

    @FXML
    private void initialize() {
        gimnasio gym = gimnasio.getInstance();
        lblNombre.setText(gym.getNombreComercial());
        lblDatos.setText("NIT " + gym.getNit() + "   ·   " + gym.getDireccion() + "   ·   Tel. " + gym.getTelefono()
                + "   ·   " + gym.getCorreo() + "   ·   " + gym.getPaginaWeb());

        tabs.getSelectionModel().selectedIndexProperty().addListener((obs, anterior, nuevo) -> refrescar(nuevo.intValue()));
    }

    private void refrescar(int indice) {
        switch (indice) {
            case 0 -> clientesController.refrescar();
            case 1 -> entrenadoresController.refrescar();
            case 2 -> planesController.refrescar();
            case 3 -> serviciosController.refrescar();
            case 4 -> inscripcionesController.refrescar();
            case 5 -> consultasController.refrescar();
            default -> { }
        }
    }
}

