package model;

public class Entrenador extends Persona{

    private String especialidad;
    private double tarifaPorSesion;


    public Entrenador(String nombre, String documento, String telefono, String correo) {
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
                '}';
    }
}
