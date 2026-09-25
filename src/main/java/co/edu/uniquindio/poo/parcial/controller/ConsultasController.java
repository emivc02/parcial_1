package co.edu.uniquindio.poo.parcial.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import co.edu.uniquindio.poo.parcial.model.Cliente;
import co.edu.uniquindio.poo.parcial.model.GestorClientes;
import co.edu.uniquindio.poo.parcial.model.GestorInscripciones;
import co.edu.uniquindio.poo.parcial.model.Inscripcion;
import co.edu.uniquindio.poo.parcial.model.gimnasio;

import java.time.LocalDate;

/**
 * Controlador de la pestaña Consultas:
 *  1) Buscar un cliente por teléfono y saber si el número es perfecto.
 *  2) Calcular los ingresos generados por las inscripciones en un periodo.
 */
public class ConsultasController {

    // Búsqueda por teléfono
    @FXML private TextField txtTelefono;
    @FXML private Button btnBuscar;
    @FXML private Label lblCliente;
    @FXML private Label lblPerfecto;
    @FXML private ProgressIndicator progreso;

    // Ingresos por periodo
    @FXML private DatePicker dpInicio;
    @FXML private DatePicker dpFin;
    @FXML private Label lblIngresos;
    @FXML private TableView<Inscripcion> tablaPeriodo;
    @FXML private TableColumn<Inscripcion, String> colId;
    @FXML private TableColumn<Inscripcion, String> colFecha;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, String> colValor;

    private final GestorClientes gestorClientes = new GestorClientes();
    private final GestorInscripciones gestorInscripciones = new GestorInscripciones();

    @FXML
    private void initialize() {
        progreso.setVisible(false);
        dpInicio.setValue(LocalDate.now().withDayOfMonth(1));
        dpFin.setValue(LocalDate.now());

        colId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        colFecha.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getFecha())));
        colCliente.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCliente().getNombre()));
        colPlan.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlanEntrenamiento().getNombre()));
        colValor.setCellValueFactory(c -> new SimpleStringProperty(Util.moneda(c.getValue().getValorTotal())));
    }

    public void refrescar() {

    }

    @FXML
    private void buscarPorTelefono() {
        String telefono = txtTelefono.getText() == null ? "" : txtTelefono.getText().trim();
        if (telefono.isEmpty()) {
            Util.error("Escriba un número de teléfono.");
            return;
        }

        Cliente cliente = gestorClientes.buscarPorTelefono(telefono);
        if (cliente == null) {
            lblCliente.setText("No hay ningún cliente con el teléfono " + telefono + ".");
        } else {
            lblCliente.setText(cliente.getNombre() + "   ·   Documento: " + cliente.getDocumento()
                    + "   ·   Correo: " + cliente.getCorreo() + "   ·   Edad: " + cliente.getEdad());
        }

        long numero;
        try {
            numero = Long.parseLong(telefono);
        } catch (NumberFormatException e) {
            lblPerfecto.setText("El teléfono contiene caracteres que no son números.");
            return;
        }

        Task<Boolean> tarea = new Task<>() {
            @Override
            protected Boolean call() {
                return gestorClientes.esNumeroPerfecto(numero);
            }
        };
        tarea.setOnRunning(e -> {
            progreso.setVisible(true);
            btnBuscar.setDisable(true);
            lblPerfecto.setText("Calculando…");
            lblPerfecto.getStyleClass().removeAll("si", "no");
        });
        tarea.setOnSucceeded(e -> {
            progreso.setVisible(false);
            btnBuscar.setDisable(false);
            boolean perfecto = tarea.getValue();
            lblPerfecto.setText(perfecto
                    ? "Sí: " + numero + " es un número perfecto (la suma de sus divisores propios es igual al número)."
                    : "No: " + numero + " no es un número perfecto.");
            lblPerfecto.getStyleClass().add(perfecto ? "si" : "no");
        });
        tarea.setOnFailed(e -> {
            progreso.setVisible(false);
            btnBuscar.setDisable(false);
            lblPerfecto.setText("No se pudo calcular.");
        });
        Thread hilo = new Thread(tarea);
        hilo.setDaemon(true);
        hilo.start();
    }

    @FXML
    private void calcularIngresos() {
        LocalDate inicio = dpInicio.getValue();
        LocalDate fin = dpFin.getValue();
        if (inicio == null || fin == null) {
            Util.error("Seleccione la fecha de inicio y la fecha de fin.");
            return;
        }
        if (fin.isBefore(inicio)) {
            Util.error("La fecha de fin no puede ser anterior a la fecha de inicio.");
            return;
        }

        double total = gestorInscripciones.calcularIngresosPorPeriodo(inicio, fin);
        lblIngresos.setText(Util.moneda(total));

        tablaPeriodo.setItems(FXCollections.observableArrayList(gimnasio.getInstance().getListaInscripciones().stream()
                .filter(i -> i.getFecha() != null && !i.getFecha().isBefore(inicio) && !i.getFecha().isAfter(fin))
                .toList()));
    }
}
