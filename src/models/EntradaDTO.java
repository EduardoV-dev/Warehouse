package models;

import java.util.Date;

public class EntradaDTO {
    private int id_entrada;
    private int productoID;
    private int cantidad;
    private int usuarioID;
    private String observaciones;
    private Date fechaEntrada;


    public EntradaDTO(int id_entrada, int productoID, int cantidad, int usuarioID, String observaciones, Date fechaEntrada) {
        this.id_entrada = id_entrada;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.usuarioID = usuarioID;
        this.observaciones = observaciones;
        this.fechaEntrada = fechaEntrada;
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
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

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
}
