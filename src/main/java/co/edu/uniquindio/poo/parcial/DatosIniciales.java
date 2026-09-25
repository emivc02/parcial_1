package co.edu.uniquindio.poo.parcial;

import co.edu.uniquindio.poo.parcial.model.*;

import java.time.LocalDate;

/**
 * Carga datos de ejemplo para poder probar la aplicación desde el inicio.
 * Los planes se crean con las fábricas (patrón Factory Method)
 * y las inscripciones con el Builder.
 */
public class DatosIniciales {

    public static void cargar() {
        gimnasio gym = gimnasio.getInstance();
        if (!gym.getListaClientes().isEmpty()) {
            return; // ya se cargaron
        }

        // Datos del gimnasio
        gym.setNombreComercial("SmartGym");
        gym.setNit("900.123.456-7");
        gym.setDireccion("Calle 10 # 20-30, Armenia");
        gym.setTelefono("6067451234");
        gym.setCorreo("contacto@smartgym.com");
        gym.setPaginaWeb("www.smartgym.com");

        try {
            // Entrenadores
            Entrenador e1 = new Entrenador("Juan Perez", "1001", "300123", "juan@mail.com", "Musculación", 40000);
            Entrenador e2 = new Entrenador("Ana Lopez", "1002", "300456", "ana@mail.com", "Crossfit", 45000);
            gym.getListaEntrenadores().add(e1);
            gym.getListaEntrenadores().add(e2);

            // Clientes
            Cliente c1 = new Cliente("Carlos Diaz", "2001", "310111", "carlos@mail.com", 25, LocalDate.now());
            Cliente c2 = new Cliente("Maria Ruiz", "2002", "310222", "maria@mail.com", 30, LocalDate.now());
            gym.getListaClientes().add(c1);
            gym.getListaClientes().add(c2);

            // Planes
            PlanEntrenamiento p1 = new PlanBasicoFactory().crearPlan("PB-1", "Básico Inicial", "Acceso maquinas", 1, 80000);
            PlanEntrenamiento p2 = new PlanPremiumFactory().crearPlan("PP-1", "Premium Pro", "Clases y maquinas", 1, 120000);
            PlanEntrenamiento p3 = new PlanPersonalizadoFactory(12, "Crossfit", "Bajar peso").crearPlan("123", "Personalizado Full", "Con entrenador", 1, 200000);
            gym.getListaPlanes().add(p1);
            gym.getListaPlanes().add(p2);
            gym.getListaPlanes().add(p3);

            // Servicios Adicionales
            ServicioAdicional s1 = new ServicioAdicional("1234", "Nutricionista", "Plan de alimentación", 50000, true);
            ServicioAdicional s2 = new ServicioAdicional("123", "Piscina", "Acceso zona húmeda", 30000, true);
            gym.getListaServicios().add(s1);
            gym.getListaServicios().add(s2);

            // Inscripciones
            Inscripcion i1 = new Inscripcion.Builder()
                    .id("INS-001")
                    .cliente(c1)
                    .planEntrenamiento(p1)
                    .agregarServicio(s1)
                    .fecha(LocalDate.now())
                    .build();
            
            Inscripcion i2 = new Inscripcion.Builder()
                    .id("INS-002")
                    .cliente(c2)
                    .planEntrenamiento(p3)
                    .entrenador(e2)
                    .agregarServicio(s1)
                    .agregarServicio(s2)
                    .fecha(LocalDate.now())
                    .build();
            
            gym.getListaInscripciones().add(i1);
            gym.getListaInscripciones().add(i2);
            
        } catch (Exception e) {
            System.err.println("Error cargando datos quemados: " + e.getMessage());
        }
    }
}