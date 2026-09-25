package co.edu.uniquindio.poo.parcial.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

/**
 * Utilidades compartidas por los controladores: mensajes y formato de dinero.
 */
public final class Util {

    private static final NumberFormat MONEDA = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    private Util() {
    }

    public static String moneda(double valor) {
        return MONEDA.format(valor);
    }

    public static void info(String mensaje) {
        mostrar(Alert.AlertType.INFORMATION, "Información", mensaje);
    }

    public static void error(String mensaje) {
        mostrar(Alert.AlertType.ERROR, "Error", mensaje);
    }

    public static boolean confirmar(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.setTitle("Confirmar");
        Optional<ButtonType> r = alert.showAndWait();
        return r.isPresent() && r.get() == ButtonType.YES;
    }

    private static void mostrar(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo, mensaje);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.showAndWait();
    }

    /** Lanza una excepción con el mensaje dado si el texto está vacío. */
    public static String requerido(String texto, String campo) throws IllegalArgumentException {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El campo \"" + campo + "\" es obligatorio.");
        }
        return texto.trim();
    }

    public static int entero(String texto, String campo) {
        try {
            return Integer.parseInt(requerido(texto, campo));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo \"" + campo + "\" debe ser un número entero.");
        }
    }

    public static double decimal(String texto, String campo) {
        try {
            return Double.parseDouble(requerido(texto, campo).replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo \"" + campo + "\" debe ser un número.");
        }
    }
}
