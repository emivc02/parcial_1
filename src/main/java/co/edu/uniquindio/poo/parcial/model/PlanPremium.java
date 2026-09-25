package co.edu.uniquindio.poo.parcial.model;

public class PlanPremium extends PlanEntrenamiento{

    public PlanPremium(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual);
        setBeneficios("Acceso a zonas deportivas, clases grupales");
    }


}
