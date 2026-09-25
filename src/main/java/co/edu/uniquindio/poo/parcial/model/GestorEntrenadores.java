package co.edu.uniquindio.poo.parcial.model;

public class GestorEntrenadores {

    private final gimnasio gym;

    /**
     * constructor de la clase GestorEntrenadores
     */
    public GestorEntrenadores() {
        this.gym = gimnasio.getInstance();
    }

    /**
     * metodo que permite registrar entrenador
     * @param entrenador
     * @throws Exception
     */
    public void registrar(Entrenador entrenador) throws Exception {
        if (buscarPorIdentificacion(entrenador.getDocumento()) != null) {
            throw new Exception("El entrenador con la identificación dada ya está registrado.");
        }
        gym.getListaEntrenadores().add(entrenador);
    }

    /**
     * metodo que permite buscar entrenador en base a identificacion
     * @param identificacion
     * @return
     */
    public Entrenador buscarPorIdentificacion(String identificacion) {
        for (Entrenador e : gym.getListaEntrenadores()) {
            if (e.getDocumento().equals(identificacion)) {
                return e;
            }
        }
        return null;
    }

    /**
     * metodo que te permite actualizar entrenador
     * @param identificacion
     * @param nombre
     * @param telefono
     * @param correo
     * @param especialidad
     * @param tarifaPorSesion
     * @return
     */
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

    /**
     * metodo que permite eliminar entrenador
     * @param identificacion
     * @return
     */
    public boolean eliminar(String identificacion) {
        Entrenador entrenador = buscarPorIdentificacion(identificacion);
        if (entrenador != null) {
            gym.getListaEntrenadores().remove(entrenador);
            return true;
        }
        return false;
    }
}
