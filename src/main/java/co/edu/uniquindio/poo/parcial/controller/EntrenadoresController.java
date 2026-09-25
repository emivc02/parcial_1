package co.edu.uniquindio.poo.parcial.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial.model.Entrenador;
import co.edu.uniquindio.poo.parcial.model.GestorEntrenadores;
import co.edu.uniquindio.poo.parcial.model.gimnasio;

public class EntrenadoresController {

    @FXML private TextField txtDocumento;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtTarifa;

    @FXML private TableView<Entrenador> tabla;
    @FXML private TableColumn<Entrenador, String> colDocumento;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colTelefono;
    @FXML private TableColumn<Entrenador, String> colCorreo;
    @FXML private TableColumn<Entrenador, String> colEspecialidad;
    @FXML private TableColumn<Entrenador, String> colTarifa;

    private final GestorEntrenadores gestor = new GestorEntrenadores();

    @FXML
    private void initialize() {
        colDocumento.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDocumento()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colTelefono.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
        colCorreo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreo()));
        colEspecialidad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEspecialidad()));
        colTarifa.setCellValueFactory(c -> new SimpleStringProperty(Util.moneda(c.getValue().getTarifaPorSesion())));

        tabla.getSelectionModel().selectedItemProperty().addListener((obs, a, e) -> mostrar(e));
        refrescar();
    }

    public void refrescar() {
        tabla.setItems(FXCollections.observableArrayList(gimnasio.getInstance().getListaEntrenadores()));
        tabla.refresh();
    }

    @FXML
    private void registrar() {
        try {
            Entrenador e = new Entrenador(
                    Util.requerido(txtNombre.getText(), "Nombre"),
                    Util.requerido(txtDocumento.getText(), "Identificación"),
                    Util.requerido(txtTelefono.getText(), "Teléfono"),
                    Util.requerido(txtCorreo.getText(), "Correo"),
                    Util.requerido(txtEspecialidad.getText(), "Especialidad"),
                    Util.decimal(txtTarifa.getText(), "Tarifa por sesión"));
            gestor.registrar(e);
            refrescar();
            limpiar();
            Util.info("Entrenador registrado correctamente.");
        } catch (Exception ex) {
            Util.error(ex.getMessage());
        }
    }

    @FXML
    private void actualizar() {
        try {
            String id = Util.requerido(txtDocumento.getText(), "Identificación");
            boolean ok = gestor.actualizar(id,
                    Util.requerido(txtNombre.getText(), "Nombre"),
                    Util.requerido(txtTelefono.getText(), "Teléfono"),
                    Util.requerido(txtCorreo.getText(), "Correo"),
                    Util.requerido(txtEspecialidad.getText(), "Especialidad"),
                    Util.decimal(txtTarifa.getText(), "Tarifa por sesión"));
            if (ok) {
                refrescar();
                Util.info("Entrenador actualizado.");
            } else {
                Util.error("No existe un entrenador con la identificación " + id + ".");
            }
        } catch (Exception ex) {
            Util.error(ex.getMessage());
        }
    }

    @FXML
    private void eliminar() {
        try {
            String id = Util.requerido(txtDocumento.getText(), "Identificación");
            if (!Util.confirmar("¿Eliminar el entrenador con identificación " + id + "?")) {
                return;
            }
            if (gestor.eliminar(id)) {
                refrescar();
                limpiar();
                Util.info("Entrenador eliminado.");
            } else {
                Util.error("No existe un entrenador con la identificación " + id + ".");
            }
        } catch (Exception ex) {
            Util.error(ex.getMessage());
        }
    }

    @FXML
    private void limpiar() {
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEspecialidad.clear();
        txtTarifa.clear();
        txtDocumento.setDisable(false);
        tabla.getSelectionModel().clearSelection();
    }

    private void mostrar(Entrenador e) {
        if (e == null) {
            return;
        }
        txtDocumento.setText(e.getDocumento());
        txtNombre.setText(e.getNombre());
        txtTelefono.setText(e.getTelefono());
        txtCorreo.setText(e.getCorreo());
        txtEspecialidad.setText(e.getEspecialidad());
        txtTarifa.setText(String.valueOf(e.getTarifaPorSesion()));
    }
}