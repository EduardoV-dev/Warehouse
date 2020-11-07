package models.POJO;

public class Producto {
    private String idProducto, nombre, marca;
    private int idMedida, idEstado;
    private String RIF;

    public Producto(String idProducto, String nombre, String marca, int idMedida, int idEstado, String RIF) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.idMedida = idMedida;
        this.idEstado = idEstado;
        this.RIF = RIF;
    }

    public Producto() {
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(int idMedida) {
        this.idMedida = idMedida;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getRIF() {
        return RIF;
    }

    public void setRIF(String RIF) {
        this.RIF = RIF;
    }
}
