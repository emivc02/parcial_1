package model;

import co.edu.uniquindio.poo.parcial.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PlanEntrenamientoTest {

    @Test
    void calcularValorMeses() {

            Cliente cliente = new Cliente("Carlos", "1094", "3104567890", "carlos@mail.com", 31, LocalDate.now());
            PlanEntrenamiento plan = new PlanBasicoFactory().crearPlan("PB-01", "Basico", "", 3, 100000);
            ServicioAdicional servicio = new ServicioAdicional("SA-01", "Valoracion", "", 100000, true);

            Inscripcion inscripcion = new Inscripcion.Builder()
                    .id("INS-002")
                    .cliente(cliente)
                    .planEntrenamiento(plan)
                    .agregarServicio(servicio)
                    .descuento(new DescuentoPorcentaje(10))
                    .build();

            assertEquals(300000, plan.calcularValorMeses());
            assertEquals(360000, inscripcion.getValorTotal());
        }
}
