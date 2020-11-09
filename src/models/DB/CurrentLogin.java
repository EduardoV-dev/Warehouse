package models.DB;

import models.POJO.Empresa;
import models.POJO.Usuario;

public class CurrentLogin {
    private static Empresa currentEmpresa;
    private static Usuario currentUsuario;

    public static Empresa getCurrentEmpresa() {
        return currentEmpresa;
    }

    public static void setCurrentEmpresa(Empresa currentEmpresa) {
        CurrentLogin.currentEmpresa = currentEmpresa;
    }

    public static Usuario getCurrentUsuario() {
        return currentUsuario;
    }

    public static void setCurrentUsuario(Usuario currentUsuario) {
        CurrentLogin.currentUsuario = currentUsuario;
    }
}
