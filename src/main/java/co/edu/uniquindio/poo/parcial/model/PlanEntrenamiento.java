package co.edu.uniquindio.poo.parcial.model;

public abstract class PlanEntrenamiento {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private String beneficios;
    private EstadoPlan estado;

    /**
     * constructor de la clase PlanEntrenamiento
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     */
    public PlanEntrenamiento(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = EstadoPlan.ACTIVO;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(double valorMensual) {
        this.valorMensual = valorMensual;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public EstadoPlan getEstado() {
        return estado;
    }

    public void setEstado(EstadoPlan estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PlanEntrenamiento{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracionMeses=" + duracionMeses +
                ", valorMensual=" + valorMensual +
                ", beneficios='" + beneficios + '\'' +
                ", estado=" + estado +
                '}';
    }

    /**
     * metodo que calcula el valor por meses
     * @return
     */
    public double calcularValorMeses() {
        return valorMensual * duracionMeses;
    }
}
