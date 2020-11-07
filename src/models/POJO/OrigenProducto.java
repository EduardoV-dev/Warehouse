package models.POJO;

public class OrigenProducto {
    private String idProducto, idProveedor;

    public OrigenProducto(String idProducto, String idProveedor) {
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
    }

    public OrigenProducto() {
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
}
