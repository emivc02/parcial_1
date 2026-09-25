package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GestorInscripcionesTest {

    @Test
    void calcularIngresosPorPeriodo() {

        Cliente cliente = new Cliente("Ana", "3001", "3152223344", "ana@correo.com", 22, LocalDate.now());
        PlanEntrenamiento plan = new PlanBasicoFactory().crearPlan("PB-10", "Basico", "Prueba", 3, 100000);

        gimnasio.getInstance().getListaInscripciones().add(new Inscripcion.Builder().id("I1")
                .cliente(cliente).planEntrenamiento(plan).fecha(LocalDate.of(2030, 9, 5)).build());
        gimnasio.getInstance().getListaInscripciones().add(new Inscripcion.Builder().id("I2")
                .cliente(cliente).planEntrenamiento(plan).fecha(LocalDate.of(2030, 12, 1)).build());

        double ingresos = new GestorInscripciones()
                .calcularIngresosPorPeriodo(LocalDate.of(2030, 9, 1), LocalDate.of(2030, 9, 30));

        assertEquals(300000, ingresos);
    }
}