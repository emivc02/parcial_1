package model;

public class PlanBasico extends PlanEntrenamiento{

    public PlanBasico(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual);
        setBeneficios("Acceso a zonas deportivas");
    }
}
