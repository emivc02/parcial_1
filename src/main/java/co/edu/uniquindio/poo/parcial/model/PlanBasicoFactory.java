package co.edu.uniquindio.poo.parcial.model;

public class PlanBasicoFactory extends PlanFactory {

    /**
     * metodo que crea un plan basico
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @return
     */
    @Override
    public PlanEntrenamiento crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        return new PlanBasico(codigo, nombre, descripcion, duracionMeses, valorMensual);
    }
}
