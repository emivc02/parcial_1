package co.edu.uniquindio.poo.parcial.model;

public class PlanBasico extends PlanEntrenamiento{

    /**
     * constructor de la clase PlanBasico
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     */
    public PlanBasico(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual);
        setBeneficios("Acceso a zonas deportivas");
    }
}
