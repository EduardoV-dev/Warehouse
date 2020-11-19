package models.DB;


import data.DatosEmpresa;
import models.POJO.Usuario;

public class CurrentLogin {

    private static Usuario currentUsuario;


    public static Usuario getCurrentUsuario() {
        return currentUsuario;
    }

    public static void setCurrentUsuario(Usuario currentUsuario) {
        CurrentLogin.currentUsuario = currentUsuario;
    }

    public static Empresa getCurrentEmpresa() {
        return DatosEmpresa.obtenerDatosEmpresa();
    }

    public static void setEmpresa(Empresa empresa) {
        DatosEmpresa.establecerEmpresa(empresa);
    }
}
