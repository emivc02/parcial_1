package co.edu.uniquindio.poo.parcial.model;

public class PlanPremium extends PlanEntrenamiento{

    /**
     * constructor de la clase PlanPremium
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     */
    public PlanPremium(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual);
        setBeneficios("Acceso a zonas deportivas, clases grupales");
    }


}
