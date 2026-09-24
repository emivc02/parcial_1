package model;

public abstract class PlanFactory {

    public abstract PlanEntrenamiento crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual);
}
