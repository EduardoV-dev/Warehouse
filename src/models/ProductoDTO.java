package models;

public class ProductoDTO {
    private int id_producto;
    private String codigo;
    private String nombre;
    private String marca;
    private int medidaID;
    private int stock;
    private String estado;
    private int proveedorID;

    public ProductoDTO(int id_producto, String codigo, String nombre, String marca, int medidaID, int stock, String estado, int proveedorID) {
        this.id_producto = id_producto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.medidaID = medidaID;
        this.stock = stock;
        this.estado = estado;
        this.proveedorID = proveedorID;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public int getMedidaID() {
        return medidaID;
    }

    public void setMedidaID(int medidaID) {
        this.medidaID = medidaID;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getProveedorID() {
        return proveedorID;
    }

    public void setProveedorID(int proveedorID) {
        this.proveedorID = proveedorID;
    }
}
