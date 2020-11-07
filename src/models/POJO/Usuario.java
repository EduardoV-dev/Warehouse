package models.POJO;

public class Usuario {
    private String usuario, contrasena;
    private int idRol;

    public Usuario(String usuario, String contrasena, int idRol) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.idRol = idRol;
    }

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
