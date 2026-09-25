package co.edu.uniquindio.poo.parcial.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Cliente;
import model.GestorClientes;
import model.gimnasio;

import java.time.LocalDate;

public class SmartGymController {

    private final GestorClientes gestorClientes = new GestorClientes();
    @FXML private TextField txtNombreCliente;
    @FXML private TextField txtDocumentoCliente;
    @FXML private TextField txtTelefonoCliente;
    @FXML private TextField txtCorreoCliente;
    @FXML private TextField txtEdadCliente;
    @FXML private Label lblMensajeCliente;

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colNombreCliente;
    @FXML private TableColumn<Cliente, String> colDocumentoCliente;
    @FXML private TableColumn<Cliente, String> colTelefonoCliente;
    @FXML private TableColumn<Cliente, String> colCorreoCliente;
    @FXML private TableColumn<Cliente, Number> colEdadCliente;
    @FXML private TableColumn<Cliente, String> colFechaCliente;

    @FXML private Label lblTotalClientes;
    @FXML private Label lblTotalEntrenadores;

    private gimnasio gym;
    private ObservableList<Cliente> listaClientesObservable;

    @FXML
    public void initialize() {
        gym = gimnasio.getInstance();
        listaClientesObservable = FXCollections.observableArrayList(gym.getListaClientes());

        // Configurar columnas
        colNombreCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colDocumentoCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDocumento()));
        colTelefonoCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefono()));
        colCorreoCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCorreo()));
        colEdadCliente.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getEdad()));
        colFechaCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaRegistro().toString()));

        tablaClientes.setItems(listaClientesObservable);

        // Seleccionar cliente en la tabla
        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtNombreCliente.setText(newVal.getNombre());
                txtDocumentoCliente.setText(newVal.getDocumento());
                txtTelefonoCliente.setText(newVal.getTelefono());
                txtCorreoCliente.setText(newVal.getCorreo());
                txtEdadCliente.setText(String.valueOf(newVal.getEdad()));
            }
        });

        actualizarContadores();
    }

    @FXML
    private void onAgregarCliente() {
        try {
            String nombre = txtNombreCliente.getText();
            String documento = txtDocumentoCliente.getText();
            String telefono = txtTelefonoCliente.getText();
            String correo = txtCorreoCliente.getText();
            int edad = Integer.parseInt(txtEdadCliente.getText());

            if (nombre.isEmpty() || documento.isEmpty()) {
                mostrarMensaje("Nombre y documento son obligatorios", true);
                return;
            }

            if (gestorClientes.buscarPorDocumento(documento) != null) {
                mostrarMensaje("Ya existe un cliente con ese documento", true);
                return;
            }

            Cliente cliente = new Cliente(nombre, documento, telefono, correo, edad, LocalDate.now());
            try {
                gestorClientes.registrar(cliente);
            } catch (Exception e) {
                mostrarMensaje(e.getMessage(), true);
                return;
            }
            actualizarTablaClientes();
            limpiarFormularioCliente();
            mostrarMensaje("Cliente agregado correctamente", false);

        } catch (NumberFormatException e) {
            mostrarMensaje("La edad debe ser un numero valido", true);
        }
    }

    @FXML
    private void onActualizarCliente() {
        try {
            String documento = txtDocumentoCliente.getText();
            String nombre = txtNombreCliente.getText();
            String telefono = txtTelefonoCliente.getText();
            String correo = txtCorreoCliente.getText();
            int edad = Integer.parseInt(txtEdadCliente.getText());

            if (documento.isEmpty()) {
                mostrarMensaje("Selecciona un cliente para actualizar", true);
                return;
            }

            boolean resultado = gestorClientes.actualizar(documento, nombre, telefono, correo, edad);
            if (resultado) {
                actualizarTablaClientes();
                limpiarFormularioCliente();
                mostrarMensaje("Cliente actualizado correctamente", false);
            } else {
                mostrarMensaje("No se encontro el cliente", true);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("La edad debe ser un numero valido", true);
        }
    }

    @FXML
    private void onEliminarCliente() {
        String documento = txtDocumentoCliente.getText();

        if (documento.isEmpty()) {
            mostrarMensaje("Selecciona un cliente para eliminar", true);
            return;
        }

        boolean resultado = gestorClientes.eliminar(documento);
        if (resultado) {
            actualizarTablaClientes();
            limpiarFormularioCliente();
            mostrarMensaje("Cliente eliminado correctamente", false);
        } else {
            mostrarMensaje("No se encontro el cliente", true);
        }
    }

    @FXML
    private void onLimpiarCliente() {
        limpiarFormularioCliente();
        lblMensajeCliente.setText("");
    }

    private void actualizarTablaClientes() {
        listaClientesObservable.setAll(gym.getListaClientes());
        actualizarContadores();
    }

    private void limpiarFormularioCliente() {
        txtNombreCliente.clear();
        txtDocumentoCliente.clear();
        txtTelefonoCliente.clear();
        txtCorreoCliente.clear();
        txtEdadCliente.clear();
        tablaClientes.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        lblMensajeCliente.setText(mensaje);
        lblMensajeCliente.setStyle(esError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    private void actualizarContadores() {
        lblTotalClientes.setText(String.valueOf(gym.getListaClientes().size()));
        lblTotalEntrenadores.setText(String.valueOf(gym.getListaEntrenadores().size()));
    }
}
