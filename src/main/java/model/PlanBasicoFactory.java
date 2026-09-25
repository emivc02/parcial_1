package model;

public class PlanBasicoFactory extends PlanFactory {

    @Override
    public PlanEntrenamiento crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        return new PlanBasico(codigo, nombre, descripcion, duracionMeses, valorMensual);
    }
}
