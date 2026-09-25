package model;

import co.edu.uniquindio.poo.parcial.model.Cliente;
import co.edu.uniquindio.poo.parcial.model.Inscripcion;
import co.edu.uniquindio.poo.parcial.model.PlanBasicoFactory;
import co.edu.uniquindio.poo.parcial.model.PlanEntrenamiento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InscripcionTest {

    /**
     * Prueba que el Builder crea la inscripción con los datos que se le pasan.
     */
    @Test
    void calcularTotal() {
        Cliente cliente = new Cliente("Carlos", "1094", "3104567890", "carlos@mail.com", 25, LocalDate.now());
        PlanEntrenamiento plan = new PlanBasicoFactory().crearPlan("PB-01", "Basico", "", 3, 100000);

        Inscripcion inscripcion = new Inscripcion.Builder()
                .id("INS-001")
                .cliente(cliente)
                .planEntrenamiento(plan)
                .build();

        assertEquals("INS-001", inscripcion.getId());
        assertEquals("Carlos", inscripcion.getCliente().getNombre());
        assertEquals("PB-01", inscripcion.getPlanEntrenamiento().getCodigo());
    }
}