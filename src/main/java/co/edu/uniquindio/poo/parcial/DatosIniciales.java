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

    }
}