package models.POJO;

public class EntradaSalida {
    private String idProducto, observaciones;
    private int cantidadVendida;

    public EntradaSalida(String idProducto, String observaciones, int cantidadVendida) {
        this.idProducto = idProducto;
        this.observaciones = observaciones;
        this.cantidadVendida = cantidadVendida;
    }

    public EntradaSalida() {
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
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

