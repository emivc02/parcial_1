package co.edu.uniquindio.poo.parcial.model;

public class PlanPersonalizadoFactory extends PlanFactory {

    private int cantidadSesiones;
    private String especialidadRequerida;
    private String objetivoCliente;

    /**
     * constructor de la clase PlanPersonalizadoFactory
     * @param cantidadSesiones
     * @param especialidadRequerida
     * @param objetivoCliente
     */
    public PlanPersonalizadoFactory(int cantidadSesiones, String especialidadRequerida, String objetivoCliente) {
        this.cantidadSesiones = cantidadSesiones;
        this.especialidadRequerida = especialidadRequerida;
        this.objetivoCliente = objetivoCliente;
    }

    /**
     * metodo que crea un plan personalizado
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @return
     */
    @Override
    public PlanEntrenamiento crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        return new PlanPersonalizado(codigo, nombre, descripcion, duracionMeses, valorMensual, cantidadSesiones, especialidadRequerida, objetivoCliente);
    }
}
