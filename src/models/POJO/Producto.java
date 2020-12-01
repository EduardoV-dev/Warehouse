package models.POJO;

public class Producto {
    private String idProducto, nombre, marca;
    private int cantidad;
    private String medida, estado;

    public Producto() {
    }

    public Producto(String nombre, String marca, String medida, String estado) {
        this.nombre = nombre;
        this.marca = marca;
        this.medida = medida;
        this.estado = estado;
    }

    public Producto(String idProducto, String nombre, String marca, int cantidad, String medida, String estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
        this.medida = medida;
        this.estado = estado;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
