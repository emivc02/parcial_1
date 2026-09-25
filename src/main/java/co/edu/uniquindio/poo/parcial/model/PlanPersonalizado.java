package co.edu.uniquindio.poo.parcial.model;

public class PlanPersonalizado extends PlanEntrenamiento{

    private int cantidadSesiones;
    private String especialidadRequerida;
    private String objetivoCliente;

    public PlanPersonalizado(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, int cantidadSesiones, String especialidadRequerida, String objetivoCliente) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual);
        this.cantidadSesiones = cantidadSesiones;
        this.especialidadRequerida = especialidadRequerida;
        this.objetivoCliente = objetivoCliente;
        setBeneficios("Acceso a zonas deportivas, clases grupales, acompanamiento de entrenador");
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public String getEspecialidadRequerida() {
        return especialidadRequerida;
    }

    public void setEspecialidadRequerida(String especialidadRequerida) {
        this.especialidadRequerida = especialidadRequerida;
    }

    public String getObjetivoCliente() {
        return objetivoCliente;
    }

    public void setObjetivoCliente(String objetivoCliente) {
        this.objetivoCliente = objetivoCliente;
    }

    @Override
    public String toString() {
        return "PlanPersonalizado{" +
                "cantidadSesiones=" + cantidadSesiones +
                ", especialidadRequerida='" + especialidadRequerida + '\'' +
                ", objetivoCliente='" + objetivoCliente + '\'' +
                "} " + super.toString();
    }
}
