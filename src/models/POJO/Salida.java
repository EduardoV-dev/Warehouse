package models.POJO;

public class Salida {
    private int idVenta;
    private String idProducto;

    public Salida(int idVenta, String idProducto) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
    }

    public Salida() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
}
