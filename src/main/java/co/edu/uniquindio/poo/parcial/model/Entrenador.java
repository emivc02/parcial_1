package co.edu.uniquindio.poo.parcial.model;

public class Entrenador extends Persona{

    private String especialidad;
    private double tarifaPorSesion;

    /**
     * metodo constructor de la clase entrenador
     * @param nombre
     * @param documento
     * @param telefono
     * @param correo
     * @param especialidad
     * @param tarifaPorSesion
     */
    public Entrenador(String nombre, String documento, String telefono, String correo, String especialidad, double tarifaPorSesion) {
        super(nombre, documento, telefono, correo);
        this.especialidad = especialidad;
        this.tarifaPorSesion = tarifaPorSesion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public double getTarifaPorSesion() {
        return tarifaPorSesion;
    }

    public void setTarifaPorSesion(double tarifaPorSesion) {
        this.tarifaPorSesion = tarifaPorSesion;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "especialidad='" + especialidad + '\'' +
                ", tarifaPorSesion=" + tarifaPorSesion +
                "} " + super.toString();
    }
}
