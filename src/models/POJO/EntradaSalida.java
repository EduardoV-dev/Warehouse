package models.POJO;

public class EntradaSalida {
    private String nombreProducto, observaciones;
    private int cantidadVendida;

    public EntradaSalida(String nombreProducto, String observaciones, int cantidadVendida) {
        this.nombreProducto = nombreProducto;
        this.observaciones = observaciones;
        this.cantidadVendida = cantidadVendida;
    }

    public EntradaSalida() {
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
}
