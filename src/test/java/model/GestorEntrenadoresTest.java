package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorEntrenadoresTest {

    private GestorEntrenadores gestor = new GestorEntrenadores();

    @Test
    void registrarEntrenador() throws Exception {
        Entrenador entrenador = new Entrenador("Mario", "1", "1", "mario@mail.com", "Fuerza", 50000);
        gestor.registrar(entrenador);

        Entrenador encontrado = gestor.buscarPorIdentificacion("1");
        assertNotNull(encontrado);
        assertEquals("Mario", encontrado.getNombre());

        assertThrows(Exception.class, () -> gestor.registrar(entrenador));
    }

    @Test
    void actualizarEntrenador() {
        Entrenador entrenador = new Entrenador("Sofia", "2", "2", "sofia@mail.com", "Cardio", 40000);
        gimnasio.getInstance().getListaEntrenadores().add(entrenador);

        boolean actualizado = gestor.actualizar("2", "Sofia Gomez", "2", "sofiag@mail.com", "Crossfit", 50000);

        assertTrue(actualizado);
        assertEquals("Sofia Gomez", entrenador.getNombre());
        assertEquals("Crossfit", entrenador.getEspecialidad());
        assertEquals(50000, entrenador.getTarifaPorSesion());
    }

    @Test
    void eliminarEntrenador() {
        Entrenador entrenador = new Entrenador("Luis", "3", "3", "luis@mail.com", "Funcional", 30000);
        gimnasio.getInstance().getListaEntrenadores().add(entrenador);

        boolean eliminado = gestor.eliminar("3");

        assertTrue(eliminado);
        assertNull(gestor.buscarPorIdentificacion("3"));
    }
}
