package co.edu.uniquindio.poo.parcial.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial.model.Cliente;
import co.edu.uniquindio.poo.parcial.model.GestorClientes;

import java.time.LocalDate;

/**
 * Controlador de la pestaña Clientes: registrar, actualizar, eliminar y listar.
 * Toda la lógica se delega en GestorClientes (modelo).
 */
public class ClientesController {

    @FXML private TextField txtDocumento;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdad;
    @FXML private DatePicker dpFechaRegistro;

    @FXML private TableView<Cliente> tabla;
    @FXML private TableColumn<Cliente, String> colDocumento;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, Integer> colEdad;
    @FXML private TableColumn<Cliente, LocalDate> colFecha;

    private final GestorClientes gestor = new GestorClientes();

    @FXML
    private void initialize() {
        colDocumento.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDocumento()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colTelefono.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
        colCorreo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreo()));
        colEdad.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getEdad()));
        colFecha.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getFechaRegistro()));

        tabla.getSelectionModel().selectedItemProperty().addListener((obs, a, cliente) -> mostrar(cliente));
        dpFechaRegistro.setValue(LocalDate.now());
        refrescar();
    }

    public void refrescar() {
        tabla.setItems(FXCollections.observableArrayList(gestor.listarClientes()));
        tabla.refresh();
    }

    @FXML
    private void registrar() {
        try {
            Cliente cliente = new Cliente(
                    Util.requerido(txtNombre.getText(), "Nombre"),
                    Util.requerido(txtDocumento.getText(), "Documento"),
                    Util.requerido(txtTelefono.getText(), "Teléfono"),
                    Util.requerido(txtCorreo.getText(), "Correo"),
                    Util.entero(txtEdad.getText(), "Edad"),
                    dpFechaRegistro.getValue() != null ? dpFechaRegistro.getValue() : LocalDate.now());
            gestor.registrar(cliente);
            refrescar();
            limpiar();
            Util.info("Cliente registrado correctamente.");
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void actualizar() {
        try {
            String documento = Util.requerido(txtDocumento.getText(), "Documento");
            boolean ok = gestor.actualizar(documento,
                    Util.requerido(txtNombre.getText(), "Nombre"),
                    Util.requerido(txtTelefono.getText(), "Teléfono"),
                    Util.requerido(txtCorreo.getText(), "Correo"),
                    Util.entero(txtEdad.getText(), "Edad"));
            if (ok) {
                refrescar();
                Util.info("Cliente actualizado.");
            } else {
                Util.error("No existe un cliente con el documento " + documento + ".");
            }
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void eliminar() {
        try {
            String documento = Util.requerido(txtDocumento.getText(), "Documento");
            if (!Util.confirmar("¿Eliminar el cliente con documento " + documento + "?")) {
                return;
            }
            if (gestor.eliminar(documento)) {
                refrescar();
                limpiar();
                Util.info("Cliente eliminado.");
            } else {
                Util.error("No existe un cliente con el documento " + documento + ".");
            }
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void limpiar() {
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
        dpFechaRegistro.setValue(LocalDate.now());
        txtDocumento.setDisable(false);
        tabla.getSelectionModel().clearSelection();
    }

    private void mostrar(Cliente c) {
        if (c == null) {
            return;
        }
        txtDocumento.setText(c.getDocumento());
        txtNombre.setText(c.getNombre());
        txtTelefono.setText(c.getTelefono());
        txtCorreo.setText(c.getCorreo());
        txtEdad.setText(String.valueOf(c.getEdad()));
        dpFechaRegistro.setValue(c.getFechaRegistro());

    }
}
