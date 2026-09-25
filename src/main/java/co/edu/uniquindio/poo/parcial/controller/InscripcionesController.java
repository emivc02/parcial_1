package co.edu.uniquindio.poo.parcial.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.StringConverter;
import co.edu.uniquindio.poo.parcial.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InscripcionesController {

    @FXML private TextField txtId;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<PlanEntrenamiento> cbPlan;
    @FXML private ComboBox<Entrenador> cbEntrenador;
    @FXML private ListView<ServicioAdicional> lvServicios;
    @FXML private TextField txtDescuento;
    @FXML private Label lblTotal;
    @FXML private Label lblAyudaEntrenador;

    @FXML private TableView<Inscripcion> tabla;
    @FXML private TableColumn<Inscripcion, String> colId;
    @FXML private TableColumn<Inscripcion, String> colFecha;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, String> colEntrenador;
    @FXML private TableColumn<Inscripcion, String> colServicios;
    @FXML private TableColumn<Inscripcion, String> colTotal;

    private final gimnasio gym = gimnasio.getInstance();
    private final GestorInscripciones gestor = new GestorInscripciones();
    private final Map<ServicioAdicional, BooleanProperty> marcados = new HashMap<>();

    @FXML
    private void initialize() {
        convertidor(cbCliente, c -> c.getNombre() + " (" + c.getDocumento() + ")");
        convertidor(cbPlan, p -> p.getNombre() + " - " + PlanesController.tipoDe(p));
        convertidor(cbEntrenador, e -> e.getNombre() + " - " + e.getEspecialidad());

        lvServicios.setCellFactory(CheckBoxListCell.forListView(
                s -> marcados.computeIfAbsent(s, k -> nuevoMarcado()),
                new StringConverter<>() {
                    @Override public String toString(ServicioAdicional s) {
                        return s == null ? "" : s.getNombre() + "  (" + Util.moneda(s.getPrecio()) + ")";
                    }
                    @Override public ServicioAdicional fromString(String t) { return null; }
                }));

        cbPlan.valueProperty().addListener((obs, a, plan) -> {
            boolean personalizado = plan instanceof PlanPersonalizado;
            cbEntrenador.setDisable(!personalizado);
            lblAyudaEntrenador.setText(personalizado ? "Obligatorio para plan personalizado" : "Solo para planes personalizados");
            if (!personalizado) {
                cbEntrenador.setValue(null);
            }
            calcularTotal();
        });
        txtDescuento.textProperty().addListener((obs, a, b) -> calcularTotal());

        colId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        colFecha.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getFecha())));
        colCliente.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCliente().getNombre()));
        colPlan.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlanEntrenamiento().getNombre()));
        colEntrenador.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getEntrenador() == null ? "—" : c.getValue().getEntrenador().getNombre()));
        colServicios.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getServiciosAdicionales().stream()
                .map(ServicioAdicional::getNombre).collect(Collectors.joining(", "))));
        colTotal.setCellValueFactory(c -> new SimpleStringProperty(Util.moneda(c.getValue().getValorTotal())));

        txtDescuento.setText("0");
        dpFecha.setValue(LocalDate.now());
        cbEntrenador.setDisable(true);
        refrescar();
    }

    public void refrescar() {
        cbCliente.setItems(FXCollections.observableArrayList(gym.getListaClientes()));
        cbPlan.setItems(FXCollections.observableArrayList(gym.getListaPlanes().stream()
                .filter(p -> p.getEstado() == EstadoPlan.ACTIVO).toList()));
        cbEntrenador.setItems(FXCollections.observableArrayList(gym.getListaEntrenadores()));

        List<ServicioAdicional> disponibles = gym.getListaServicios().stream()
                .filter(ServicioAdicional::isDisponible).toList();
        marcados.keySet().retainAll(disponibles);
        lvServicios.setItems(FXCollections.observableArrayList(disponibles));

        tabla.setItems(FXCollections.observableArrayList(gym.getListaInscripciones()));
        tabla.refresh();
        calcularTotal();
    }

    private Inscripcion construir(String id, boolean validarCliente) {
        if (validarCliente && cbCliente.getValue() == null) {
            throw new IllegalArgumentException("Seleccione un cliente.");
        }
        if (cbPlan.getValue() == null) {
            throw new IllegalArgumentException("Seleccione un plan de entrenamiento.");
        }
        double porcentaje = txtDescuento.getText().isBlank() ? 0 : Util.decimal(txtDescuento.getText(), "Descuento (%)");
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        }

        Inscripcion.Builder builder = new Inscripcion.Builder()
                .id(id)
                .fecha(dpFecha.getValue() != null ? dpFecha.getValue() : LocalDate.now())
                .cliente(cbCliente.getValue())
                .planEntrenamiento(cbPlan.getValue())
                .entrenador(cbEntrenador.getValue())
                .descuento(new DescuentoPorcentaje(porcentaje));
        for (ServicioAdicional s : serviciosMarcados()) {
            builder.agregarServicio(s);
        }
        return builder.build();
    }

    @FXML
    private void registrar() {
        try {
            Inscripcion inscripcion = construir(Util.requerido(txtId.getText(), "ID"), true);
            gestor.registrarInscripcion(inscripcion);
            refrescar();
            limpiar();
            Util.info("Inscripción registrada.\nValor total: " + Util.moneda(inscripcion.getValorTotal()));
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void eliminar() {
        Inscripcion sel = tabla.getSelectionModel().getSelectedItem();
        String id = sel != null ? sel.getId() : txtId.getText();
        if (id == null || id.isBlank()) {
            Util.error("Seleccione una inscripción de la tabla o escriba su ID.");
            return;
        }
        if (!Util.confirmar("¿Eliminar la inscripción " + id + "?")) {
            return;
        }
        if (gestor.eliminarInscripcion(id.trim())) {
            refrescar();
            limpiar();
            Util.info("Inscripción eliminada.");
        } else {
            Util.error("No existe una inscripción con el ID " + id + ".");
        }
    }

    @FXML
    private void limpiar() {
        txtId.clear();
        dpFecha.setValue(LocalDate.now());
        cbCliente.setValue(null);
        cbPlan.setValue(null);
        cbEntrenador.setValue(null);
        txtDescuento.setText("0");
        marcados.values().forEach(p -> p.set(false));
        tabla.getSelectionModel().clearSelection();
        calcularTotal();
    }

    private void calcularTotal() {
        try {
            lblTotal.setText(cbPlan.getValue() == null ? "—" : Util.moneda(construir("tmp", false).getValorTotal()));
        } catch (Exception e) {
            lblTotal.setText("—");
        }
    }

    private List<ServicioAdicional> serviciosMarcados() {
        return lvServicios.getItems().stream()
                .filter(s -> marcados.containsKey(s) && marcados.get(s).get())
                .toList();
    }

    private BooleanProperty nuevoMarcado() {
        BooleanProperty p = new SimpleBooleanProperty(false);
        p.addListener((obs, a, b) -> calcularTotal());
        return p;
    }

    private static <T> void convertidor(ComboBox<T> combo, Function<T, String> texto) {
        combo.setConverter(new StringConverter<>() {
            @Override public String toString(T obj) { return obj == null ? "" : texto.apply(obj); }
            @Override public T fromString(String s) { return null; }
        });
    }
}
