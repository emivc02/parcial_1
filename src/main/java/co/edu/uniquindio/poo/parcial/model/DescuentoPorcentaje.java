package co.edu.uniquindio.poo.parcial.model;

public class DescuentoPorcentaje implements Descuento {
    private double porcentaje;

    /**
     * constructor de la clase DescuentoPorcentaje
     * @param porcentaje
     */
    public DescuentoPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * metodo que aplica un descuento
     * @param total
     * @return
     */
    @Override
    public double aplicarDescuento(double total) {
        return total - (total * porcentaje / 100);
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}