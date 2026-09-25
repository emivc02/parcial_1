package model;

public class PlanPremiumFactory extends PlanFactory {

    @Override
    public PlanEntrenamiento crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        return new PlanPremium(codigo, nombre, descripcion, duracionMeses, valorMensual);
    }
}
