package models.DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class consultasDelete {
    private static Connection cn;
    private static CallableStatement cs;

    // Elimina un producto dentro de una empresa por medio de su idProducto y
    // elimina todos los registros relacionados con el
    // Devuelve 1 en caso de haber eliminado el producto y sus registros relacionados correctamente
    // Devuelve resutl <= 0 en caso de haber un error (Que el IDProducto no exista)
    public static int eliminarProducto(String idProducto) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delProducto(?)}");
        cs.setString(1, idProducto);
        return cs.executeUpdate();
    }

    // Elimina un proveedor dentro de una empresa por medio de su idProveedor y
    // elimina todos los registros relacionados con el
    // Devuelve 1 en caso de haber eliminado el proveedor y sus registros relacionados correctamente
    // Devuelve resutl <= 0 en caso de haber un error (Que el IDProveedor no exista)
    public static int eliminarProveedor(String idProveedor) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delProveedor(?)}");
        cs.setString(1, idProveedor);
        return cs.executeUpdate();
    }

    // Elimina un usuario por medio de su usuario (username)
    // No es posible eliminar el usuario admin por defecto
    // Devuelve 1 en caso de eliminar el usuario correctamente
    // Devuelve result <= 0 en caso de haber fallado (Que el usuario no existe)
    // Cuando se elimina el usuario, todos los registros relacionados a ese usuario
    // Pasan a ser propiedad del usuario admin
    public static int eliminarUsuario(String usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delUsuario(?)}");
        cs.setString(1, usuario);
        return cs.executeUpdate();
    }

    // Elimina una medida para un producto de una empresa
    // Devuelve 1 en caso de exito
    // Devuelve result <= 0 en caso de error (no exista la medida que quiere borrar)
    // No es posible borrar la medida con id = 1 (Unidades), debido a que cuando una medida se borra
    // Los que tenian la medida que se borro, pasan a obtener el id = 1
    public static int eliminarMedidaProducto(String medida) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delMedida(?)}");
        cs.setString(1, medida);
        return cs.executeUpdate();
    }

    // Elimina un estado para un producto de una empresa
    // Devuelve 1 en caso de exito
    // Devuelve result <= 0 en caso de error (no existe el estado que quiere borrar)
    // Se basa en el mismo principio que el metodo eliminarMedidaProducto
    public static int eliminarEstadoProducto(String estado) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call delEstado(?)}");
        cs.setString(1, estado);
        return cs.executeUpdate();
    }
}
