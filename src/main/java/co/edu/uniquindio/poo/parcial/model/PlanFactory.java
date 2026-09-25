package co.edu.uniquindio.poo.parcial.model;

public abstract class PlanFactory {

    /**
     * metodo que crea un plan de entrenamiento
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @return
     */
    public abstract PlanEntrenamiento crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual);
}
