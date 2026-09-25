package co.edu.uniquindio.poo.parcial.model;

public class GestorEntrenadores {

    private final gimnasio gym;

    public GestorEntrenadores() {
        this.gym = gimnasio.getInstance();
    }

    public void registrar(Entrenador entrenador) throws Exception {
        if (buscarPorIdentificacion(entrenador.getDocumento()) != null) {
            throw new Exception("El entrenador con la identificación dada ya está registrado.");
        }
        gym.getListaEntrenadores().add(entrenador);
    }

    public Entrenador buscarPorIdentificacion(String identificacion) {
        for (Entrenador e : gym.getListaEntrenadores()) {
            if (e.getDocumento().equals(identificacion)) {
                return e;
            }
        }
        return null;
    }

    public boolean actualizar(String identificacion, String nombre, String telefono, String correo, String especialidad, double tarifaPorSesion) {
        Entrenador entrenador = buscarPorIdentificacion(identificacion);
        if (entrenador != null) {
            entrenador.setNombre(nombre);
            entrenador.setTelefono(telefono);
            entrenador.setCorreo(correo);
            entrenador.setEspecialidad(especialidad);
            entrenador.setTarifaPorSesion(tarifaPorSesion);
            return true;
        }
        return false;
    }

    public boolean eliminar(String identificacion) {
        Entrenador entrenador = buscarPorIdentificacion(identificacion);
        if (entrenador != null) {
            gym.getListaEntrenadores().remove(entrenador);
            return true;
        }
        return false;
    }
}
