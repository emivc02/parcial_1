package model;

import java.time.LocalDate;

public class Cliente extends Persona{

    private int edad;
    private final LocalDate fechaRegistro;

    public Cliente(String nombre, String documento, String telefono, String correo, LocalDate fechaRegistro) {
        super(nombre, documento, telefono, correo);
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "edad=" + edad +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
