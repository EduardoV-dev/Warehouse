package models.DB;

import models.POJO.Producto;
import models.POJO.Proveedor;
import models.POJO.EntradaSalida;
import models.POJO.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class consultasInsert {
    private static Connection cn;
    private static CallableStatement cs;

    // Metodo para crear usuarios en una empresa
    // Devolverá false en caso de error (Que ya exista un usuario con el mismo username)
    public static boolean crearUsuario(Usuario usuario, String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insUsuario(?,?,?,?)}");
        cs.setString(1, usuario.getUsuario());
        cs.setString(2, usuario.getContrasena());
        cs.setString(3, usuario.getRol());
        cs.setString(4, RIF);
        return cs.execute();
    }

    // Metodo para agregar proveedores en una empresa
    // Devolverá false en caso de error (Que ya exista un proveedor con el mismo idProveedor)
    public static boolean agregarProveedor(Proveedor proveedor, String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insProveedor(?,?,?,?,?,?)}");
        cs.setString(1, proveedor.getIdProveedor());
        cs.setString(2, proveedor.getNombres());
        cs.setString(3, proveedor.getApellidos());
        cs.setString(4, proveedor.getCorreo());
        cs.setString(5, proveedor.getTelefono());
        cs.setString(6, RIF);
        return cs.execute();
    }

    // Metodo para crear una nueva medida de un producto en caso de necesitarla
    public static boolean nuevaMedida(String medida) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insMedida(?)}");
        cs.setString(1, medida);
        return cs.execute();
    }

    // Metodo para crear un nuevo estado para un producto
    public static boolean nuevoEstado(String estado) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insEstado(?)}");
        cs.setString(1, estado);
        return cs.execute();
    }

    // Agrega un nuevo producto a la empresa
    public static boolean agregarProducto(Producto producto, String nombreProveedor, String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insProducto(?,?,?,?,?,?,?,?)}");
        cs.setString(1, producto.getIdProducto());
        cs.setString(2, producto.getNombre());
        cs.setString(3, producto.getMarca());
        cs.setInt(4, producto.getCantidad());
        cs.setString(5, producto.getMedida());
        cs.setString(6, producto.getEstado());
        cs.setString(7, nombreProveedor);
        cs.setString(8, RIF);
        return cs.execute();
    }

    // Realiza una venta de un producto, el metodo t-sql se encarga de deducir la cantidad
    // vendida y de registrar la venta realizada
    public static boolean realizarVentaProducto(EntradaSalida salida, String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insSalida(?,?,?,?)}");
        cs.setString(1, salida.getNombreProducto());
        cs.setInt(2, salida.getCantidadVendida());
        cs.setString(3, salida.getObservaciones());
        cs.setString(4, RIF);
        return cs.execute();
    }

    // Realiza un ingreso de un producto, el metodo t-sql se encarga de adicionar la cantidad
    // ingresada, así como registrar al proveedor de la entrada
    public static boolean realizarIngresoProducto(EntradaSalida entrada, String nombreProveedor, String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insEntrada(?,?,?,?,?)}");
        cs.setString(1, entrada.getNombreProducto());
        cs.setString(2, nombreProveedor);
        cs.setInt(3, entrada.getCantidadVendida());
        cs.setString(4, entrada.getObservaciones());
        cs.setString(5, RIF);
        return cs.execute();
    }
}
