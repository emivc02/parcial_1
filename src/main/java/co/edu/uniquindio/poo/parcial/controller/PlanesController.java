package co.edu.uniquindio.poo.parcial.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import co.edu.uniquindio.poo.parcial.model.*;

/**
 * Controlador de la pestaña Planes.
 * Los planes se crean con el patrón Factory Method: según el tipo elegido
 * se usa PlanBasicoFactory, PlanPremiumFactory o PlanPersonalizadoFactory.
 */
public class PlanesController {

    private static final String BASICO = "Básico";
    private static final String PREMIUM = "Premium";
    private static final String PERSONALIZADO = "Personalizado";

    @FXML private ComboBox<String> cbTipo;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtValorMensual;
    @FXML private ComboBox<EstadoPlan> cbEstado;

    @FXML private GridPane panelPersonalizado;
    @FXML private TextField txtSesiones;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtObjetivo;

    @FXML private TableView<PlanEntrenamiento> tabla;
    @FXML private TableColumn<PlanEntrenamiento, String> colCodigo;
    @FXML private TableColumn<PlanEntrenamiento, String> colTipo;
    @FXML private TableColumn<PlanEntrenamiento, String> colNombre;
    @FXML private TableColumn<PlanEntrenamiento, String> colDuracion;
    @FXML private TableColumn<PlanEntrenamiento, String> colValorMensual;
    @FXML private TableColumn<PlanEntrenamiento, String> colValorTotal;
    @FXML private TableColumn<PlanEntrenamiento, String> colEstado;
    @FXML private TableColumn<PlanEntrenamiento, String> colBeneficios;

    private final gimnasio gym = gimnasio.getInstance();

    @FXML
    private void initialize() {
        cbTipo.setItems(FXCollections.observableArrayList(BASICO, PREMIUM, PERSONALIZADO));
        cbTipo.setValue(BASICO);
        cbTipo.valueProperty().addListener((obs, a, tipo) -> actualizarPanelPersonalizado());
        cbEstado.setItems(FXCollections.observableArrayList(EstadoPlan.values()));
        cbEstado.setValue(EstadoPlan.ACTIVO);
        actualizarPanelPersonalizado();

        colCodigo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCodigo()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(tipoDe(c.getValue())));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colDuracion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDuracionMeses() + " meses"));
        colValorMensual.setCellValueFactory(c -> new SimpleStringProperty(Util.moneda(c.getValue().getValorMensual())));
        colValorTotal.setCellValueFactory(c -> new SimpleStringProperty(Util.moneda(c.getValue().calcularValorMeses())));
        colEstado.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getEstado())));
        colBeneficios.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBeneficios()));

        tabla.getSelectionModel().selectedItemProperty().addListener((obs, a, plan) -> mostrar(plan));
        refrescar();
    }

    public void refrescar() {
        tabla.setItems(FXCollections.observableArrayList(gym.getListaPlanes()));
        tabla.refresh();
    }

    /** Elige la fábrica concreta según el tipo de plan (Factory Method). */
    private PlanFactory fabricaPara(String tipo) {
        return switch (tipo) {
            case PREMIUM -> new PlanPremiumFactory();
            case PERSONALIZADO -> new PlanPersonalizadoFactory(
                    Util.entero(txtSesiones.getText(), "Cantidad de sesiones"),
                    Util.requerido(txtEspecialidad.getText(), "Especialidad requerida"),
                    Util.requerido(txtObjetivo.getText(), "Objetivo del cliente"));
            default -> new PlanBasicoFactory();
        };
    }

    @FXML
    private void registrar() {
        try {
            String codigo = Util.requerido(txtCodigo.getText(), "Código");
            if (gym.buscarPlanPorCodigo(codigo) != null) {
                throw new IllegalArgumentException("Ya existe un plan con el código " + codigo + ".");
            }
            PlanFactory fabrica = fabricaPara(cbTipo.getValue());
            PlanEntrenamiento plan = fabrica.crearPlan(codigo,
                    Util.requerido(txtNombre.getText(), "Nombre"),
                    Util.requerido(txtDescripcion.getText(), "Descripción"),
                    Util.entero(txtDuracion.getText(), "Duración (meses)"),
                    Util.decimal(txtValorMensual.getText(), "Valor mensual"));
            plan.setEstado(cbEstado.getValue());
            gym.agregarPlan(plan);
            refrescar();
            limpiar();
            Util.info("Plan " + cbTipo.getValue() + " registrado correctamente.");
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void actualizar() {
        try {
            String codigo = Util.requerido(txtCodigo.getText(), "Código");
            boolean ok = gym.actualizarPlan(codigo,
                    Util.requerido(txtNombre.getText(), "Nombre"),
                    Util.requerido(txtDescripcion.getText(), "Descripción"),
                    Util.entero(txtDuracion.getText(), "Duración (meses)"),
                    Util.decimal(txtValorMensual.getText(), "Valor mensual"));
            if (!ok) {
                Util.error("No existe un plan con el código " + codigo + ".");
                return;
            }
            PlanEntrenamiento plan = gym.buscarPlanPorCodigo(codigo);
            plan.setEstado(cbEstado.getValue());
            if (plan instanceof PlanPersonalizado p) {
                p.setCantidadSesiones(Util.entero(txtSesiones.getText(), "Cantidad de sesiones"));
                p.setEspecialidadRequerida(Util.requerido(txtEspecialidad.getText(), "Especialidad requerida"));
                p.setObjetivoCliente(Util.requerido(txtObjetivo.getText(), "Objetivo del cliente"));
            }
            refrescar();
            Util.info("Plan actualizado.");
        } catch (Exception e) {
            Util.error(e.getMessage());
        }
    }

    @FXML
    private void eliminar() {
        try {
            String codigo = Util.requerido(txtCodigo.getText(), "Código");
            if (!Util.confirmar("¿Eliminar el plan " + codigo + "?")) {
                return;
            }
            if (gym.eliminarPlan(codigo)) {
                refrescar();
                limpiar();
                Util.info("Plan eliminado.");
            } else {
                Util.error("No existe un plan con el código " + codigo + ".");
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
        txtDuracion.clear();
        txtValorMensual.clear();
        txtSesiones.clear();
        txtEspecialidad.clear();
        txtObjetivo.clear();
        cbEstado.setValue(EstadoPlan.ACTIVO);
        cbTipo.setDisable(false);
        txtCodigo.setDisable(false);
        tabla.getSelectionModel().clearSelection();
    }

    private void mostrar(PlanEntrenamiento plan) {
        if (plan == null) {
            return;
        }
        cbTipo.setValue(tipoDe(plan));
        txtCodigo.setText(plan.getCodigo());
        txtNombre.setText(plan.getNombre());
        txtDescripcion.setText(plan.getDescripcion());
        txtDuracion.setText(String.valueOf(plan.getDuracionMeses()));
        txtValorMensual.setText(String.valueOf(plan.getValorMensual()));
        cbEstado.setValue(plan.getEstado());
        if (plan instanceof PlanPersonalizado p) {
            txtSesiones.setText(String.valueOf(p.getCantidadSesiones()));
            txtEspecialidad.setText(p.getEspecialidadRequerida());
            txtObjetivo.setText(p.getObjetivoCliente());
        } else {
            txtSesiones.clear();
            txtEspecialidad.clear();
            txtObjetivo.clear();
        }

    }

    private void actualizarPanelPersonalizado() {
        boolean personalizado = PERSONALIZADO.equals(cbTipo.getValue());
        panelPersonalizado.setDisable(!personalizado);
    }

    static String tipoDe(PlanEntrenamiento plan) {
        if (plan instanceof PlanPersonalizado) {
            return PERSONALIZADO;
        }
        if (plan instanceof PlanPremium) {
            return PREMIUM;
        }
        return BASICO;
    }
}
