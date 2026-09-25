package model;

import co.edu.uniquindio.poo.parcial.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PlanEntrenamientoTest {

    /**
     * metodo que permite probar el calculo del valor de los meses
     */
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

    /**
     * metodo que permite probar la creacion de planes mediante el uso de las fabricas
     */
    @Test
    void crearPlanesConFabricas() {
        PlanEntrenamiento planBasico = new PlanBasicoFactory()
                .crearPlan("PB-1", "Basico", "", 1, 100000);
        PlanEntrenamiento planPremium = new PlanPremiumFactory()
                .crearPlan("PP-1", "Premium", "", 2, 100000);
        PlanEntrenamiento planPersonalizado = new PlanPersonalizadoFactory(5, "Fuerza", "Objetivo")
                .crearPlan("PZ-1", "Personalizado", "", 3, 100000);

        assertEquals(100000, planBasico.calcularValorMeses());
        assertEquals(200000, planPremium.calcularValorMeses());
        assertEquals(300000, planPersonalizado.calcularValorMeses());

        assertTrue(planBasico instanceof PlanBasico);
        assertTrue(planPremium instanceof PlanPremium);
        assertTrue(planPersonalizado instanceof PlanPersonalizado);

        PlanPersonalizado personalizado = (PlanPersonalizado) planPersonalizado;
        assertEquals(5, personalizado.getCantidadSesiones());
        assertEquals("Fuerza", personalizado.getEspecialidadRequerida());
    }

    /**
     * metodo que permite probar el registro de un servicio adicional
     */
    @Test
    void registrarServicioAdicional() {
        ServicioAdicional servicio = new ServicioAdicional("1", "Nutricion", "", 50000, true);

        assertEquals("1", servicio.getCodigo());
        assertEquals("Nutricion", servicio.getNombre());
        assertEquals(50000, servicio.getPrecio());
        assertTrue(servicio.isDisponible());
    }
}
