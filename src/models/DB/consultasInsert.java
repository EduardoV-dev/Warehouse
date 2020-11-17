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

    // Metodo para crear usuarios
    // Devolverá 1 en caso de que el usuario haya sido creado exitosamente
    // Devolverá un numero menor o igual a 0 (result <= 0) en caso de error (Que ya exista un usuario con el mismo username)
    public static int crearUsuario(Usuario usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insUsuario(?,?,?,?)}");
        cs.setString(1, usuario.getUsuario());
        cs.setString(2, usuario.getContrasena());
        cs.setString(3, usuario.getRol());
        return cs.executeUpdate();
    }

    // Metodo para agregar proveedores
    // Devolverá 1 en caso de que la insercion haya sido exitosa
    // Devolverá un numero menor o igual a 0 (result <= 0) en caso de error (Que ya exista un proveedor con el mismo idProveedor)
    public static int agregarProveedor(Proveedor proveedor, String usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insProveedor(?,?,?,?,?,?)}");
        cs.setString(1, proveedor.getIdProveedor());
        cs.setString(2, proveedor.getNombres());
        cs.setString(3, proveedor.getApellidos());
        cs.setString(4, proveedor.getCorreo());
        cs.setString(5, proveedor.getTelefono());
        cs.setString(6, usuario);
        return cs.executeUpdate();
    }

    // Metodo para crear una nueva medida de un producto en caso de necesitarla
    // Devuelve 1 en caso de que la medida se haya creado con exito
    // Devuelve un numero menor o igual a 0 (result <= 0) en caso de que haya un error (Por que ya existe la medida)
    public static int nuevaMedida(String medida) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insMedida(?)}");
        cs.setString(1, medida);
        return cs.executeUpdate();
    }

    // Metodo para crear un nuevo estado para un producto
    // Devuelve 1 en caso de que el estado se haya creado con exito
    // Devuelve un numero menor o igual a 0 (result <= 0) en caso de que haya un error (Por que ya existe el estado)
    public static int nuevoEstado(String estado) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insEstado(?)}");
        cs.setString(1, estado);
        return cs.executeUpdate();
    }

    // Agrega un nuevo producto
    // Devuelve 1 en caso de haber insertado el producto correctamente
    // Devuelve (result <= 0) en caso de haber un error, el error se puede por que el idProducto esta siendo
    // usado por otra empresa
    public static int agregarProducto(Producto producto, String nombreProveedor, String usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insProducto(?,?,?,?,?,?,?,?)}");
        cs.setString(1, producto.getIdProducto());
        cs.setString(2, producto.getNombre());
        cs.setString(3, producto.getMarca());
        cs.setInt(4, producto.getCantidad());
        cs.setString(5, producto.getMedida());
        cs.setString(6, producto.getEstado());
        cs.setString(7, nombreProveedor);
        cs.setString(8, usuario);
        return cs.executeUpdate();
    }

    // Realiza una venta de un producto, el metodo t-sql se encarga de deducir la cantidad
    // vendida y de registrar la venta realizada
    // Devuelve 1 en caso de que la venta sea realizada con exito
    // Devuelve result <= 0 en caso de que la venta tenga un error
    public static int realizarVentaProducto(EntradaSalida salida, String usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insSalida(?,?,?,?,?)}");
        cs.setString(1, salida.getIdProducto());
        cs.setInt(2, salida.getCantidadVendida());
        cs.setString(3, salida.getObservaciones());
        cs.setString(4, usuario);
        return cs.executeUpdate();
    }

    // Realiza un ingreso de un producto, el metodo t-sql se encarga de adicionar la cantidad
    // ingresada, así como registrar al proveedor de la entrada
    // Devuelve 1 en caso de que el ingreso haya sido exitoso
    // Devuelve result <= 0 en caso de que haya un error
    public static int realizarIngresoProducto(EntradaSalida entrada, String nombreProveedor, String usuario) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call insEntrada(?,?,?,?,?)}");
        cs.setString(1, entrada.getIdProducto());
        cs.setString(2, nombreProveedor);
        cs.setInt(3, entrada.getCantidadVendida());
        cs.setString(4, entrada.getObservaciones());
        cs.setString(5, usuario);
        return cs.executeUpdate();
    }
}
