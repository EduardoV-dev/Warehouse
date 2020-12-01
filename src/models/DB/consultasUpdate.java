package models.DB;

import models.POJO.Producto;
import models.POJO.Proveedor;
import models.POJO.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class consultasUpdate {
    private static Connection cn;
    private static CallableStatement cs;

    // Actualiza los siguientes datos de un producto
    // nombre, marca, estado y medida
    // Devuelve 1 en caso de que la actualizacion haya sido exitosa
    // Devuelve result <= 0 en caso de error
    public static int actualizarProducto(Producto producto) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call updActualizarProducto(?,?,?,?,?)}");
        cs.setString(1, producto.getIdProducto());
        cs.setString(2, producto.getNombre());
        cs.setString(3, producto.getMarca());
        cs.setString(4, producto.getEstado());
        cs.setString(5, producto.getMedida());
        return cs.executeUpdate();
    }

    // Actualiza los siguientes datos de un proveedor
    // nombres, apellidos, correo y telefono
    public static int actualizarProveedor(Proveedor proveedor) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call updActualizarProveedor(?,?,?,?,?)}");
        cs.setString(1, proveedor.getIdProveedor());
        cs.setString(2, proveedor.getNombres());
        cs.setString(3, proveedor.getApellidos());
        cs.setString(4, proveedor.getCorreo());
        cs.setString(5, proveedor.getTelefono());
        return cs.executeUpdate();
    }

    // Actualiza el rol de un usuario
    public static int actualizarUsuario(Usuario usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call updActualizarUsuario(?,?)}");
        cs.setString(1, usuario.getUsuario());
        cs.setString(2, usuario.getRol());
        return cs.executeUpdate();
    }

    // Actualiza la contraseña de un usuario
    public static int actualizarEmpresa(Usuario usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call updActualizarUsuario(?,?)}");
        cs.setString(1, usuario.getUsuario());
        cs.setString(2, usuario.getContrasena());
        return cs.executeUpdate();
    }
}
