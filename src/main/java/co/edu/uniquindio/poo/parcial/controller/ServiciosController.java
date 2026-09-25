package co.edu.uniquindio.poo.parcial.controller;

import co.edu.uniquindio.poo.parcial.model.ServicioAdicional;
import co.edu.uniquindio.poo.parcial.model.gimnasio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ServiciosController {


    @FXML
    private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private CheckBox chkDisponible;

    @FXML private TableView<ServicioAdicional> tabla;
    @FXML private TableColumn<ServicioAdicional, String> colCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colNombre;
    @FXML private TableColumn<ServicioAdicional, String> colDescripcion;
    @FXML private TableColumn<ServicioAdicional, String> colPrecio;
    @FXML private TableColumn<ServicioAdicional, String> colDisponible;

    private final gimnasio gym = gimnasio.getInstance();

    @FXML
    private void initialize() {
        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigo()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colDescripcion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));
        colPrecio.setCellValueFactory(c -> new SimpleStringProperty(Util.moneda(c.getValue().getPrecio())));
        colDisponible.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().isDisponible() ? "Sí" : "No"));

        tabla.getSelectionModel().selectedItemProperty().addListener((obs, a, s) -> mostrar(s));
        chkDisponible.setSelected(true);
        refrescar();
    }

    public void refrescar() {
        tabla.setItems(FXCollections.observableArrayList(gym.getListaServicios()));
        tabla.refresh();
    }

    @FXML
    private void registrar() {
        try {
            String codigo = Util.requerido(txtCodigo.getText(), "Código");
            if (gym.buscarServicioPorCodigo(codigo) != null) {
                throw new IllegalArgumentException("Ya existe un servicio con el código " + codigo + ".");
            }
            gym.agregarServicio(new ServicioAdicional(codigo,
                    Util.requerido(txtNombre.getText(), "Nombre"),
                    Util.requerido(txtDescripcion.getText(), "Descripción"),
                    Util.decimal(txtPrecio.getText(), "Precio"),
                    chkDisponible.isSelected()));
            refrescar();
            limpiar();
            Util.info("Servicio registrado correctamente.");
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void actualizar() {
        try {
            String codigo = Util.requerido(txtCodigo.getText(), "Código");
            boolean ok = gym.actualizarServicio(codigo,
                    Util.requerido(txtNombre.getText(), "Nombre"),
                    Util.requerido(txtDescripcion.getText(), "Descripción"),
                    Util.decimal(txtPrecio.getText(), "Precio"),
                    chkDisponible.isSelected());
            if (ok) {
                refrescar();
                Util.info("Servicio actualizado.");
            } else {
                Util.error("No existe un servicio con el código " + codigo + ".");
            }
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void eliminar() {
        try {
            String codigo = Util.requerido(txtCodigo.getText(), "Código");
            if (!Util.confirmar("¿Eliminar el servicio " + codigo + "?")) {
                return;
            }
            if (gym.eliminarServicio(codigo)) {
                refrescar();
                limpiar();
                Util.info("Servicio eliminado.");
            } else {
                Util.error("No existe un servicio con el código " + codigo + ".");
            }
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void limpiar() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        chkDisponible.setSelected(true);
        txtCodigo.setDisable(false);
        tabla.getSelectionModel().clearSelection();
    }

    private void mostrar(ServicioAdicional s) {
        if (s == null) {
            return;
        }
        txtCodigo.setText(s.getCodigo());
        txtNombre.setText(s.getNombre());
        txtDescripcion.setText(s.getDescripcion());
        txtPrecio.setText(String.valueOf(s.getPrecio()));
        chkDisponible.setSelected(s.isDisponible());
    }
}
