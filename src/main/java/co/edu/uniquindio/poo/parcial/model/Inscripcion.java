package co.edu.uniquindio.poo.parcial.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscripcion {

    private String id;
    private LocalDate fecha;
    private double valorTotal;
    private Cliente cliente;
    private PlanEntrenamiento planEntrenamiento;
    private Entrenador entrenador;
    private List<ServicioAdicional> serviciosAdicionales;
    private Descuento descuento;

    /**
     * metodo que permite crear inscripciones de forma flexible
     * @param builder
     */
    private Inscripcion(Builder builder) {
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.valorTotal = builder.valorTotal;
        this.cliente = builder.cliente;
        this.planEntrenamiento = builder.planEntrenamiento;
        this.entrenador = builder.entrenador;
        this.serviciosAdicionales = builder.serviciosAdicionales;
        this.descuento = builder.descuento;
        this.valorTotal = calcularTotal();
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PlanEntrenamiento getPlanEntrenamiento() {
        return planEntrenamiento;
    }

    public void setPlanEntrenamiento(PlanEntrenamiento planEntrenamiento) {
        this.planEntrenamiento = planEntrenamiento;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public Descuento getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id='" + id + '\'' +
                ", fecha=" + fecha +
                ", valorTotal=" + valorTotal +
                ", cliente=" + cliente +
                ", planEntrenamiento=" + planEntrenamiento +
                ", entrenador=" + entrenador +
                ", serviciosAdicionales=" + serviciosAdicionales +
                '}';
    }

    /**
     * metodo que permite calcular el total de una inscripcion
     * @return
     */
    public double calcularTotal() {
        double total = planEntrenamiento.calcularValorMeses();
        if (serviciosAdicionales != null) {
            for (ServicioAdicional servicio : serviciosAdicionales) {
                total += servicio.getPrecio();
            }
        }
        if (descuento != null) {
            return descuento.aplicarDescuento(total);
        }
        return total;
    }

    /**
     * metodo que permite agregar un servicio adicional a la inscripcion
     * @param servicio
     */
    public void agregarServicio(ServicioAdicional servicio) {
        serviciosAdicionales.add(servicio);
        valorTotal = calcularTotal();
    }


    public static class Builder {

        private String id;
        private LocalDate fecha;
        private double valorTotal;
        private Cliente cliente;
        private PlanEntrenamiento planEntrenamiento;
        private Entrenador entrenador;
        private List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();
        private Descuento descuento = new DescuentoPorcentaje(0);

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder valorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
            return this;
        }

        public Builder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder planEntrenamiento(PlanEntrenamiento planEntrenamiento) {
            this.planEntrenamiento = planEntrenamiento;
            return this;
        }

        public Builder entrenador(Entrenador entrenador) {
            this.entrenador = entrenador;
            return this;
        }

        public Builder serviciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
            this.serviciosAdicionales = serviciosAdicionales;
            return this;
        }

        public Builder agregarServicio(ServicioAdicional servicio) {
            this.serviciosAdicionales.add(servicio);
            return this;
        }

        public Builder descuento(Descuento descuento) {
            this.descuento = descuento;
            return this;
        }

        public Inscripcion build() {
            return new Inscripcion(this);
        }


    }

}
