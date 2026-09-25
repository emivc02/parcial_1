package co.edu.uniquindio.poo.parcial.model;

import java.time.LocalDate;

public class Cliente extends Persona{

    private int edad;
    private LocalDate fechaRegistro;

    public Cliente(String nombre, String documento, String telefono, String correo, int edad, LocalDate fechaRegistro) {
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

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "edad=" + edad +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
