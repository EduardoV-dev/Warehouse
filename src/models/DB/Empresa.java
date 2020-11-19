package models.DB;

public class Empresa {
    private String nombre;
    private String correo;
    private String telefono;
    private String RIF;
    private String direccion;

    public Empresa() {

    }

    public Empresa(String nombre, String correo, String telefono, String RIF,  String direccion) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.RIF = RIF;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRIF() {
        return RIF;
    }

    public void setRIF(String RIF) {
        this.RIF = RIF;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
