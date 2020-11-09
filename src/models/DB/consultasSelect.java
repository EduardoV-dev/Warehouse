package models.DB;

import models.POJO.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consultasSelect {
    private static Connection cn;
    private static CallableStatement cs;

    public static ResultSet totalVentas(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalVentas(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }


    public static ResultSet totalProductos(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalProductos(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

    public static ResultSet totalUsuarios(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalUsuarios(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

    // Opc = 0; obtiene el top 5 de los últimos productos vendidos
    // Opc = 1; obtiene el top 5 de los productos más vendidos
    public static ResultSet topProductos(String RIF, int opc) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTopProductos(?,?)}");
        cs.setString(1, RIF);
        cs.setInt(2, opc);
        return cs.executeQuery();
    }

    public static ResultSet departamentos() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selDepartamentos}");
        return cs.executeQuery();
    }

    // Obtiene las ventas realizadas en los últimos 7 días
    public static ResultSet ventas7dias(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selVentasSiete(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

    // Obtiene el historial de ventas completo de una empresa, ordenado por la venta más reciente
    // Hasta la venta menos reciente
    // Devuelve -> String RIF, IDProducto, Producto
    //             int Cantidad
    //             String Fecha
    public static ResultSet historialVentas(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selHistorialVentas(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

    // Obtiene la información de una empresa
    // Devuelve -> String RIF, nombre, correo, direccion, telefono, departamento
    public static ResultSet informacionEmpresa(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoEmpresa(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

    // Obtiene la informacion de productos de una empresa
    // Opc = 0, obtiene la informacion de un producto filtrado por su nombre
    // Opc = 1, obtiene la informacion de todos los productos de una empresa
    // Cuando se usa Opc = 0, se deja vacio el parametro nombreProducto ya que no se va a usar
    // Devuelve -> String IDProducto, Producto, Marca
    //             int Cantidad
    //             String Estado, Medida, Proveedor
    public static ResultSet informacionProductos(int opc, String RIF, String nombreProducto) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoProducto(?,?,?)}");
        cs.setString(1, RIF);
        cs.setString(2, nombreProducto);
        cs.setInt(3, opc);
        return cs.executeQuery();
    }

    // Obtiene la informacion de proveedores de una empresa
    // Opc = 0, obtiene la informacion de un proveedor filtrado por su nombre completo
    // Opc = 1, obtiene la informacion de los proveedores registrados en una empresa
    // Devuelve -> String IDProveedor, Nombres, Apellidos, Correo, Telefono
    // Opc = 2, obtiene los nombres completos de los proveedores registrados en una empresa
    // Usar la opcion 2 para llenar los comboBox
    // Devuelve -> String nombreCompleto
    // Al usar las opciones Opc = 1 y Opc = 2, se puede dejar vacio el parametro nombreProveedor
    public static ResultSet informacionProveedores(int opc, String RIF, String nombreProveedor) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoProveedor(?,?,?)}");
        cs.setString(1, RIF);
        cs.setString(2, nombreProveedor);
        cs.setInt(3, opc);
        return cs.executeQuery();
    }

    // Obtiene la informacion de usuarios en la empresa
    // Opc = 0, obtiene la informacion de un usuario filtrado por su username (Usuario)
    // Opc = 1, obtiene la informacion de todos los usuarios
    // Devuelve -> String Usuario, Rol
    public static ResultSet informacionUsuarios(int opc, String RIF, String usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoUsuario(?,?,?)}");
        cs.setString(1, RIF);
        cs.setString(2, usuario);
        cs.setInt(3, opc);
        return cs.executeQuery();
    }

    // Obtiene las unidades de medida para los productos
    // Devuelve -> String medida
    // El metodo esta pensado para llenar comboBox
    public static ResultSet medidasProducto() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selMedidaProducto}");
        return cs.executeQuery();
    }

    // Obtiene las estados para los productos
    // Devuelve -> String estado
    // El metodo esta pensado para llenar comboBox
    public static ResultSet estadosProducto() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selEstadoProducto}");
        return cs.executeQuery();
    }
}
