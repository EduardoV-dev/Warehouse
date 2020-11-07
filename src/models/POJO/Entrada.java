package models.POJO;

public class Entrada {
    private int idAdquisicion;
    private String idProducto;

    public Entrada(int idAdquisicion, String idProducto) {
        this.idAdquisicion = idAdquisicion;
        this.idProducto = idProducto;
    }

    public Entrada() {
    }

    public int getIdAdquisicion() {
        return idAdquisicion;
    }

    public void setIdAdquisicion(int idAdquisicion) {
        this.idAdquisicion = idAdquisicion;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
}
