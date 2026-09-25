package model;

import co.edu.uniquindio.poo.parcial.model.Cliente;
import co.edu.uniquindio.poo.parcial.model.GestorClientes;
import co.edu.uniquindio.poo.parcial.model.gimnasio;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GestorClientesTest {

    private GestorClientes gestor = new GestorClientes();

    @Test
    void buscarPorTelefono() {
        Cliente cliente = new Cliente("Laura", "2001", "3001112233",
                "laura@correo.com", 25, LocalDate.now());
        gimnasio.getInstance().getListaClientes().add(cliente);

        Cliente encontrado = gestor.buscarPorTelefono("3001112233");

        assertNotNull(encontrado);
        assertEquals("Laura", encontrado.getNombre());
        assertNull(gestor.buscarPorTelefono("0000000000"));
    }

    @Test
    void esNumeroPerfecto() {

        assertTrue(gestor.esNumeroPerfecto(6));
        assertTrue(gestor.esNumeroPerfecto(28));
        assertTrue(gestor.esNumeroPerfecto(8589869056L));

        assertFalse(gestor.esNumeroPerfecto(12));
        assertFalse(gestor.esNumeroPerfecto(3001112233L));
    }
}