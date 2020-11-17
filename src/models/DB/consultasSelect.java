package models.DB;

import models.POJO.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consultasSelect {
    private static Connection cn;
    private static CallableStatement cs;

    // Devuelve -> String usuario, contraseña
    public static ResultSet ingresar(Usuario usuario) throws SQLException{
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoLogin(?,?)}");
        cs.setString(1, usuario.getUsuario());
        cs.setString(2, usuario.getContrasena());
        return cs.executeQuery();
    }

    // Devuelve el total de ventas que se han realizado
    // Devuelve -> int totalVentas
    public static ResultSet totalVentas() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalVentas()}");
        return cs.executeQuery();
    }

    // Devuelve el total de productos registrados
    // Devuelve -> int totalProductos
    public static ResultSet totalProductos() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalProductos()}");
        return cs.executeQuery();
    }

    // Devuelve el total de usuarios registrados
    // Devuelve -> int usuariosTotales
    public static ResultSet totalUsuarios() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalUsuarios()}");
        return cs.executeQuery();
    }

    // Opc = 0; obtiene el top 5 de los últimos productos vendidos
    // los ultimos productos vendidos pueden ser productos repetidos
    // Opc = 1; obtiene el top 5 de los productos más vendidos
    // los productos mas vendidos no son repetidos
    // Devuelve -> String Producto,
    //             int Ventas
    // Usando opc = 0, la columna de ventas queda vacia
    public static ResultSet topProductos(int opc) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTopProductos(?)}");
        cs.setInt(1, opc);
        return cs.executeQuery();
    }

    // Obtiene las ventas realizadas en los últimos 7 días
    public static ResultSet ventas7dias() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selVentasSiete()}");
        return cs.executeQuery();
    }

    // Opc = 0, Obtiene el historial de ventas completo de una empresa
    // Opc = 1, Obtiene el historial de ventas en una fecha concreta
    // Los resultados son ordenados por la venta más reciente, si la fecha es igual
    // Se ordena por nombre del producto
    // Hasta la venta menos reciente
    // Formato de fecha: 01/01/2000
    // Devuelve -> String IDProducto, Producto
    //             int Cantidad
    //             String Fecha, Gestor (usuario que realizó la venta)
    public static ResultSet historialVentas(String fecha, int opc) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selHistorialVentas(?,?)}");
        cs.setString(1, fecha);
        cs.setInt(2, opc);
        return cs.executeQuery();
    }

    // Obtiene la informacion de productos de una empresa
    // Opc = 0, obtiene la informacion de un producto filtrado por su nombre
    // Opc = 1, obtiene la informacion de todos los productos
    // Cuando se usa Opc = 0, se deja vacio el parametro nombreProducto ya que no se va a usar
    // Devuelve -> String IDProducto, Producto, Marca
    //             int Cantidad
    //             String Estado, Medida
    public static ResultSet informacionProductos(int opc, String nombreProducto) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoProducto(?,?)}");
        cs.setString(1, nombreProducto);
        cs.setInt(2, opc);
        return cs.executeQuery();
    }

    // Obtiene la informacion de proveedores de una empresa
    // Opc = 0, obtiene la informacion de un proveedor filtrado por su nombre completo
    // Opc = 1, obtiene la informacion de los proveedores registrados en una empresa
    // Devuelve -> String IDProveedor, Nombres, Apellidos, Correo, Telefono, Gestor (El que registró al proveedor)
    // Al usar las opciones Opc = 1 y Opc = 2, se puede dejar vacio el parametro nombreProveedor
    // Existe un problema con este metodo, y se da cuando se usa las opciones 1 y 2, SQL Server cuando
    // trata de concatenar 2 columnas (Procedimiento hecho por mi), crea un gran espacio en medio de ambas
    // La solucion de esto es hacer un trim en la primera de nombres (indice: 2) cuando se vaya a leer su contenido
    // Ejemplo: rs.getString(2).trim(), con esto se soluciona el problema
    public static ResultSet informacionProveedores(int opc, String nombreProveedor) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoProveedor(?,?)}");
        cs.setString(1, nombreProveedor);
        cs.setInt(2, opc);
        return cs.executeQuery();
    }

    // Devuelve el nombre completo de los proveedores de una empresa
    // Usar para llenar comboBox
    public static ResultSet proveedores() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selProveedores()}");
        return cs.executeQuery();
    }

    // Obtiene la informacion de usuarios en la empresa
    // Opc = 0, obtiene la informacion de un usuario filtrado por su username (Usuario)
    // Opc = 1, obtiene la informacion de todos los usuarios
    // Devuelve -> String Usuario, Rol
    public static ResultSet informacionUsuarios(int opc, String usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selInfoUsuario(?,?)}");
        cs.setString(1, usuario);
        cs.setInt(2, opc);
        return cs.executeQuery();
    }

    // Obtiene las unidades de medida para los productos
    // Devuelve -> String medida
    // El metodo esta pensado para llenar comboBox
    public static ResultSet medidasProducto() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selMedidaProducto()}");
        return cs.executeQuery();
    }

    // Obtiene las estados para los productos
    // Devuelve -> String estado
    // El metodo esta pensado para llenar comboBox
    public static ResultSet estadosProducto() throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selEstadoProducto()}");
        return cs.executeQuery();
    }

    // Metodo para verificar si un codigo, ya sea idProveedor o idProducto
    // ya existe, en caso de no existir, se procederá a realizar la inserción de dicho registro
    // En el caso de existir, se procederá a generar otro código
    // El parametro codigo es el que se genera desde la clase CodigosGenerador
    public static ResultSet verificarCodigo(String codigo, int opc) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selVerificarCodigo(?,?)}");
        cs.setString(1, codigo);
        cs.setInt(2, opc);
        return cs.executeQuery();
    }
}
