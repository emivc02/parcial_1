package model;

public class PlanPersonalizadoFactory extends PlanFactory {

    private int cantidadSesiones;
    private String especialidadRequerida;
    private String objetivoCliente;

    public PlanPersonalizadoFactory(int cantidadSesiones, String especialidadRequerida, String objetivoCliente) {
        this.cantidadSesiones = cantidadSesiones;
        this.especialidadRequerida = especialidadRequerida;
        this.objetivoCliente = objetivoCliente;
    }

    @Override
    public PlanEntrenamiento crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        return new PlanPersonalizado(codigo, nombre, descripcion, duracionMeses, valorMensual, cantidadSesiones, especialidadRequerida, objetivoCliente);
    }
}
