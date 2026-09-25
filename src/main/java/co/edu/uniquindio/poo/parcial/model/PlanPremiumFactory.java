package co.edu.uniquindio.poo.parcial.model;

public class PlanPremiumFactory extends PlanFactory {

    /**
     * metodo que crea un plan premium
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @return
     */
    @Override
    public PlanEntrenamiento crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        return new PlanPremium(codigo, nombre, descripcion, duracionMeses, valorMensual);
    }
}
