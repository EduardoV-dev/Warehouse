package models.POJO;

public class Venta {
    private int idVenta, cantidad;
    private String fechaVenta, observaciones, RIF;

    public Venta(int idVenta, int cantidad, String fechaVenta, String observaciones, String RIF) {
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.fechaVenta = fechaVenta;
        this.observaciones = observaciones;
        this.RIF = RIF;
    }

    public Venta() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRIF() {
        return RIF;
    }

    public void setRIF(String RIF) {
        this.RIF = RIF;
    }
}
