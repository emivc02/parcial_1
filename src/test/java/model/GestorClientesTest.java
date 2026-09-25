package model;

import co.edu.uniquindio.poo.parcial.model.Cliente;
import co.edu.uniquindio.poo.parcial.model.GestorClientes;
import co.edu.uniquindio.poo.parcial.model.gimnasio;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GestorClientesTest {

    private GestorClientes gestor = new GestorClientes();

    /**
     * metodo que permite probar la busqueda de cliente por telefono
     */
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

    /**
     * metodo que permite probar si un numero es perfecto
     */
    @Test
    void esNumeroPerfecto() {

        assertTrue(gestor.esNumeroPerfecto(6));
        assertTrue(gestor.esNumeroPerfecto(28));
        assertTrue(gestor.esNumeroPerfecto(8589869056L));

        assertFalse(gestor.esNumeroPerfecto(12));
        assertFalse(gestor.esNumeroPerfecto(3001112233L));
    }

    /**
     * metodo que permite probar el registro de clientes
     * @throws Exception
     */
    @Test
    void registrarCliente() throws Exception {
        Cliente cliente = new Cliente("Juan", "1", "1", "juan@mail.com", 20, LocalDate.now());
        gestor.registrar(cliente);

        Cliente encontrado = gestor.buscarPorDocumento("1");
        assertNotNull(encontrado);
        assertEquals("Juan", encontrado.getNombre());

        assertThrows(Exception.class, () -> gestor.registrar(cliente));
    }

    /**
     * metodo que permite probar la actualizacion de clientes
     */
    @Test
    void actualizarCliente() {
        Cliente cliente = new Cliente("Maria", "2", "2", "maria@mail.com", 20, LocalDate.now());
        gimnasio.getInstance().getListaClientes().add(cliente);

        boolean actualizado = gestor.actualizar("2", "Maria Jose", "2", "mariaj@mail.com", 25);

        assertTrue(actualizado);
        assertEquals("Maria Jose", cliente.getNombre());
        assertEquals(25, cliente.getEdad());
    }

    /**
     * metodo que permite probar la eliminacion de clientes
     */
    @Test
    void eliminarCliente() {
        Cliente cliente = new Cliente("Pedro", "3", "3", "pedro@mail.com", 20, LocalDate.now());
        gimnasio.getInstance().getListaClientes().add(cliente);

        boolean eliminado = gestor.eliminar("3");

        assertTrue(eliminado);
        assertNull(gestor.buscarPorDocumento("3"));
    }
}