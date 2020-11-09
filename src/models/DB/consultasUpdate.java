package models.DB;

import models.POJO.Empresa;
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
    public static boolean actualizarProducto(Producto producto, String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call updActualizarProducto(?,?,?,?,?,?)}");
        cs.setString(1, RIF);
        cs.setString(2, producto.getIdProducto());
        cs.setString(3, producto.getNombre());
        cs.setString(4, producto.getMarca());
        cs.setString(5, producto.getEstado());
        cs.setString(6, producto.getMedida());
        return cs.execute();
    }

    // Actualiza los siguientes datos de un proveedor
    // nombres, apellidos, correo y telefono
    public static boolean actualizarProveedor(Proveedor proveedor, String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call updActualizarProveedor(?,?,?,?,?,?)}");
        cs.setString(1, RIF);
        cs.setString(2, proveedor.getIdProveedor());
        cs.setString(3, proveedor.getNombres());
        cs.setString(4, proveedor.getApellidos());
        cs.setString(5, proveedor.getCorreo());
        cs.setString(6, proveedor.getTelefono());
        return cs.execute();
    }

    // Actualiza el siguiente dato de un usuario
    // Su rol :v
    public static boolean actualizarUsuario(Usuario usuario, String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call updActualizarUsuario(?,?,?)}");
        cs.setString(1, usuario.getUsuario());
        cs.setString(2, usuario.getRol());
        cs.setString(3, RIF);
    }

    // Actualiza los siguientes datos de una empresa
    // nombre, correo, direccion, telefono y departamento
    public static boolean actualizarProveedor(Empresa empresa) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call updActualizarProveedor(?,?,?,?,?,?)}");
        cs.setString(1, empresa.getRIF());
        cs.setString(2, empresa.getNombre());
        cs.setString(3, empresa.getCorreo());
        cs.setString(4, empresa.getDepartamento());
        cs.setString(5, empresa.getTelefono());
        cs.setString(6, empresa.getDepartamento());
        return cs.execute();
    }
}
