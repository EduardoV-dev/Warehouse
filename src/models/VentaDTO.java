package models;

import java.util.Date;

public class VentaDTO {
    private int id_venta;
    private int productoID;
    private int cantidad;
    private int usuarioID;
    private String observaciones;
    private Date fechaVenta;

    public VentaDTO(int id_venta, int productoID, int cantidad, int usuarioID, String observaciones, Date fechaVenta) {
        this.id_venta = id_venta;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.usuarioID = usuarioID;
        this.observaciones = observaciones;
        this.fechaVenta = fechaVenta;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getProductoID() {
        return productoID;
    }

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
