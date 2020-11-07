package models.POJO;

public class Adquisicion {
    private int idAdquisicion, cantidad;
    private String fechaEntrega, observaciones, RIF;

    public Adquisicion(int idAdquisicion, int cantidad, String fechaEntrega, String observaciones, String RIF) {
        this.idAdquisicion = idAdquisicion;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
        this.observaciones = observaciones;
        this.RIF = RIF;
    }

    public Adquisicion() {
    }

    public int getIdAdquisicion() {
        return idAdquisicion;
    }

    public void setIdAdquisicion(int idAdquisicion) {
        this.idAdquisicion = idAdquisicion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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
