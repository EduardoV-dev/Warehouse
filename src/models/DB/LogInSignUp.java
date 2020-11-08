package models.DB;

import models.POJO.Empresa;
import models.POJO.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInSignUp {
    private static Connection cn;
    private static CallableStatement cs;
    private static ResultSet rs;

    // Método para registrar una nueva empresa (SignUp)
    public static boolean registrarse(Empresa empresa, Usuario usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insEmpresaYAdmin(?,?,?,?,?,?,?,?)}");
        cs.setString(1, empresa.getRIF());
        cs.setString(2, empresa.getNombre());
        cs.setString(3, empresa.getCorreo());
        cs.setString(4, empresa.getDireccion());
        cs.setString(5, empresa.getTelefono());
        cs.setString(6, empresa.getDepartamento());
        cs.setString(7, usuario.getUsuario());
        cs.setString(8, usuario.getContrasena());
        return cs.execute();
    }

    public static boolean ingresar(String nombreEmpresa, Usuario usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoLogin(?,?,?)}");
        cs.setString(1, nombreEmpresa);
        cs.setString(2, usuario.getUsuario());
        cs.setString(3, usuario.getContrasena());
	    rs = cs.executeQuery();
        return rs.next();
    }
}
