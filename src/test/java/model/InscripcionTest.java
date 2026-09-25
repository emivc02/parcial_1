package model;

import co.edu.uniquindio.poo.parcial.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InscripcionTest {

    /**
     * metodo que permite probar la creacion de inscripcion con builder
     */
    @Test
    void creacionInscripcionConBuilder() {
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

    /**
     * metodo que permite probar si se agrega correctamente un servicio en curso
     */
    @Test
    void agregarServicioEnCurso() {
        Cliente cliente = new Cliente("Lucia", "2", "2", "lucia@mail.com", 20, LocalDate.now());
        PlanEntrenamiento plan = new PlanBasicoFactory().crearPlan("PB-2", "Basico", "", 1, 100000);

        Inscripcion inscripcion = new Inscripcion.Builder()
                .id("2")
                .cliente(cliente)
                .planEntrenamiento(plan)
                .build();

        assertEquals(100000, inscripcion.calcularTotal());

        ServicioAdicional servicio = new ServicioAdicional("SA-1", "Nutricion", "", 50000, true);
        inscripcion.agregarServicio(servicio);

        assertEquals(1, inscripcion.getServiciosAdicionales().size());
        assertEquals(150000, inscripcion.getValorTotal());
    }

    /**
     * metodo que permite probar el calculo del total con servicios y descuento
     */
    @Test
    void calcularTotalConServiciosYDescuento() {
        Cliente cliente = new Cliente("Andres", "3", "3", "andres@mail.com", 20, LocalDate.now());
        PlanEntrenamiento plan = new PlanPremiumFactory().crearPlan("PP-1", "Premium", "", 1, 100000);
        ServicioAdicional servicio = new ServicioAdicional("SA-1", "Nutricion", "", 50000, true);

        Inscripcion inscripcion = new Inscripcion.Builder()
                .id("3")
                .cliente(cliente)
                .planEntrenamiento(plan)
                .agregarServicio(servicio)
                .descuento(new DescuentoPorcentaje(10)) // 10% de 150000 = 15000 -> 135000
                .build();

        assertEquals(135000, inscripcion.calcularTotal());
    }
}