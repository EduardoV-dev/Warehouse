package models.DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class consultasDelete {
    private static Connection cn;
    private static CallableStatement cs;

    // Elimina un producto dentro de una empresa por medio de su idProducto y
    // elimina todos los registros relacionados con el
    public static boolean eliminarProducto(String RIF, String idProducto) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delProducto(?,?)}");
        cs.setString(1, RIF);
        cs.setString(2, idProducto);
        return cs.execute();
    }

    // Elimina un proveedor dentro de una empresa por medio de su idProveedor y
    // elimina todos los registros relacionados con el
    public static boolean eliminarProveedor(String RIF, String idProveedor) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delProveedor(?,?)}");
        cs.setString(1, RIF);
        cs.setString(2, idProveedor);
        return cs.execute();
    }

    // Elimina un usuario dentro de una empresa por medio de su username
    public static boolean eliminarUsuario(String RIF, String usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delUsuario(?,?)}");
        cs.setString(1, RIF);
        cs.setString(2, usuario);
        return cs.execute();
    }

    // Elimina una medida para un producto
    public static boolean eliminarMedidaProducto(String medida) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delMedida(?)}");
        cs.setString(1, medida);
        return cs.execute();
    }

    // Elimina un estado para un producto
    public static boolean eliminarEstadoProducto(String estado) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delEstado(?)}");
        cs.setString(1, estado);
        return cs.execute();
    }
}
