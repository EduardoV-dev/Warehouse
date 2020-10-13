package models;

public class UsuarioDTO {
    private int id_usuario;
    private String nombre;
    private String username;
    private int rol;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int id_usuario, String nombre, String username, int rol) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.username = username;
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
}
