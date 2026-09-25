package model;

import co.edu.uniquindio.poo.parcial.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GestorInscripcionesTest {

    /**
     * metodo que permite probar el calculo de ingresos por periodo
     */
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

    /**
     * metodo que permite probar el registro de inscripcion de un plan basico
     * @throws Exception
     */
    @Test
    void registrarInscripcionPlanBasico() throws Exception {
        GestorInscripciones gestor = new GestorInscripciones();
        Cliente cliente = new Cliente("Diego", "1", "1", "diego@mail.com", 20, LocalDate.now());
        PlanEntrenamiento plan = new PlanBasicoFactory().crearPlan("PB-1", "Basico", "", 1, 100000);

        Inscripcion inscripcion = new Inscripcion.Builder()
                .id("1")
                .cliente(cliente)
                .planEntrenamiento(plan)
                .fecha(LocalDate.now())
                .build();

        gestor.registrarInscripcion(inscripcion);

        Inscripcion encontrada = gestor.buscarInscripcionPorId("1");
        assertNotNull(encontrada);
        assertEquals("1", encontrada.getId());
    }

    /**
     * metodo que permite probar el registro de inscripcion de un plan personalizado
     * @throws Exception
     */
    @Test
    void registrarInscripcionPlanPersonalizado() throws Exception {
        GestorInscripciones gestor = new GestorInscripciones();
        Cliente cliente = new Cliente("Valeria", "2", "2", "valeria@mail.com", 20, LocalDate.now());
        PlanEntrenamiento plan = new PlanPersonalizadoFactory(10, "Fuerza", "Objetivo")
                .crearPlan("PP-1", "Personalizado", "", 1, 150000);
        Entrenador entrenador = new Entrenador("Andres", "1", "1", "andres@mail.com", "Fuerza", 50000);

        Inscripcion inscripcion = new Inscripcion.Builder()
                .id("2")
                .cliente(cliente)
                .planEntrenamiento(plan)
                .entrenador(entrenador)
                .fecha(LocalDate.now())
                .build();

        gestor.registrarInscripcion(inscripcion);

        Inscripcion encontrada = gestor.buscarInscripcionPorId("2");
        assertNotNull(encontrada);
        assertEquals("Andres", encontrada.getEntrenador().getNombre());
    }

    /**
     * metodo que permite probar la validacion de error si se asigna entrenador a un plan basico
     */
    @Test
    void validarErrorEntrenadorEnPlanBasico() {
        GestorInscripciones gestor = new GestorInscripciones();
        Cliente cliente = new Cliente("Camilo", "3", "3", "camilo@mail.com", 20, LocalDate.now());
        PlanEntrenamiento plan = new PlanBasicoFactory().crearPlan("PB-2", "Basico", "", 1, 100000);
        Entrenador entrenador = new Entrenador("Andres", "1", "1", "andres@mail.com", "Fuerza", 50000);

        Inscripcion inscripcion = new Inscripcion.Builder()
                .id("3")
                .cliente(cliente)
                .planEntrenamiento(plan)
                .entrenador(entrenador)
                .build();

        assertThrows(Exception.class, () -> gestor.registrarInscripcion(inscripcion));
    }
}