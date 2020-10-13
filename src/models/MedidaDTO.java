package models;

public class MedidaDTO {
    private int id_medida;
    private String nombre;

    public MedidaDTO(int id_medida, String nombre) {
        this.id_medida = id_medida;
        this.nombre = nombre;
    }

    public int getId_medida() {
        return id_medida;
    }

    public void setId_medida(int id_medida) {
        this.id_medida = id_medida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
