package models.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.POJO.*;
import utils.Converter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Facade {
    private static ResultSet rs;

    /************************************************ NOTA *********************************************/
    // El uso de throws se debe a que se pueda controlar los errores relacionados con que
    // un registro ya exista, de esta manera se puede personalizar la visualización de un error
    // más facilmente

    //Metodos para el login
    public static boolean registrarse(Empresa empresa, Usuario usuario) throws SQLException {
        return LogInSignUp.registrarse(empresa, usuario);
    }

    public static boolean ingresar(String nombreEmpresa, Usuario usuario) throws SQLException {
        return LogInSignUp.ingresar(nombreEmpresa, usuario);
    }


    //Metodos Crud Usuarios

    /*
     *   Metodo para agregar usuarios
     *   @argumentos:
     *       RIF: String del RIF de la empresa
     *       usuario: Usuario que se va a crear en la base de datos
     */
    public boolean agregarUsuario(String RIF, Usuario usuario) throws SQLException {
        return consultasInsert.crearUsuario(usuario, RIF);
    }

    /*
     *   Metodo para obtener a todos los usuarios
     *   @argumentos:
     *       RIF: String con el RIF de la empresa
     *   @retorna: ResultSet con todos los usuarios de la empresa
     */
    public ResultSet obtenerUsuarios(String RIF) throws SQLException {
        return consultasSelect.informacionUsuarios(1, RIF, "");
    }

    /*
     *  Metodo para obtener a usuarios filtrado por su nombre
     *  @argumentos:
     *      RIF: String con el RIF de la empresa
     *      usuario: String que contenga la palabra de filtro
     */
    public ResultSet filtrarUsuarios(String RIF, String usuario) throws SQLException {
        return consultasSelect.informacionUsuarios(0, RIF, usuario);
    }

    /*
     *   Metodo para modificar al usuario
     *   @argumentos:
     *       RIF: String con el RIF de la empresa
     *       Usuario: Usuario que se desea modificar
     *   @retorna:
     *       boleaan: true si se modificó, false si no
     */
    public boolean modificarUsuario(String RIF, Usuario usuario) throws SQLException {
        return consultasUpdate.actualizarUsuario(usuario, RIF);
    }

    /*
     *   Metodo para eliminar usuarios
     *   @argumentos:
     *       RIF: String del RIF de la empresa
     *       id: String del id del usuario que se va a eliminar
     */
    public boolean eliminarUsuario(String RIF, String id) throws SQLException {
        return consultasDelete.eliminarUsuario(RIF, id);
    }


    //Metodos CRUD Productos

    /*
     *   Metodo para agregar productos
     *   retonar booleando: false si ya existe producto con ese nombre, true si el producto se inserto correctamente
     */
    public static boolean agregarProducto(Producto p, String nombreProveedor, String RIF) throws SQLException {
        return consultasInsert.agregarProducto(p, nombreProveedor, RIF);
    }

    /*
     * Metodo para obtener los productos
     * retonar resultset con todos los productos de la empresa
     */
    public static ResultSet obtenerProductos(String RIF) throws SQLException {
        return consultasSelect.informacionProductos(1, RIF, "");
    }

    public static ResultSet filtrarProductos(String RIF, String producto) throws SQLException {
        return consultasSelect.informacionProductos(0, RIF, producto);
    }

    public static boolean modificarProducto(String RIF, Producto producto) throws SQLException {
        return consultasUpdate.actualizarProducto(producto, RIF);
    }

    public static boolean eliminarProducto(String RIF, String idProducto) throws SQLException {
        return consultasDelete.eliminarProducto(RIF, idProducto);
    }


    //Metodos CRUD Entradas

    public static boolean agregarEntrada(String RIF, EntradaSalida entrada, String proveedor) throws SQLException {
        return consultasInsert.realizarIngresoProducto(entrada, proveedor, RIF);
    }


    //Metodos CRUD Salidas

    public static boolean agregarSalida(String RIF, EntradaSalida salida) throws SQLException {
        return consultasInsert.realizarVentaProducto(salida, RIF);
    }


    //Metodos CRUD Proveedores

    /*
     *   Metodo para agregar proveedor
     *   retonar booleando: false si ya existe proveedor con ese nombre, true si el producto se inserto correctamente
     */
    public static boolean agregarProveedor(Proveedor p, String RIF) throws SQLException {
        return consultasInsert.agregarProveedor(p, RIF);
    }

    /*
     *  Metodo para obtener todos los proveedores de la empresa
     *  parametros:
     *      El RIF de la empresa
     *  retorna:
     *      ResultSet con los datos de los proveedores
     */
    public static ResultSet obtenerProveedores(String RIF) throws SQLException {
        return consultasSelect.informacionProveedores(1, RIF, "");
    }

    /*
     *   Metodo para eliminar proveedor
     *   @argumentos:
     *       RIF: String con el RIF de la empresa
     *       id: String con el id del proveedor que se desea eliminar
     *   @retorna:
     *       boolean: true si se pudo eliminar, false si no
     */
    public static boolean eliminarProveedor(String RIF, String id) throws SQLException {
        return consultasDelete.eliminarProveedor(RIF, id);
    }

    //Metodos CRUD Medida

    /*
     *   Metodo para agregar medida
     *   retonar booleando: false si ya existe medida con ese nombre, true si el producto se inserto correctamente
     */
    public static boolean agregarMedida(String medida) throws SQLException {
        return consultasInsert.nuevaMedida(medida);
    }

    /*
     *  Metodo para obtener todos las medidas de la empresa para tableview
     *  @parametros:
     *      RIF: El RIF de la empresa
     *  @retorna:
     *      Resultset con los datos de las medidas
     */
    public static ResultSet obtenerMedidas(String RIF) throws SQLException {
        return consultasSelect.medidasProducto();
    }

    /*
     *  Metodo para obtener todos las medidas de la empresa para combobox
     *  @parametros:
     *      RIF: El RIF de la empresa
     *  @retorna:
     *      ObservableList con los datos de las medidas
     */
    public static ObservableList<String> obtenerMedidasList(String RIF) throws SQLException {
        return Converter.resultSetToObservableList(consultasSelect.estadosProducto(), 1);
    }


    //Metodos CRUD estado
    /*
     *   Metodo para agregar estado
     *   retonar booleando: false si ya existe estado con ese nombre, true si el producto se inserto correctamente
     */
    public static boolean agregarEstado(String estado) throws SQLException {
        return consultasInsert.nuevoEstado(estado);
    }

    public static ResultSet obtenerEstados(String RIF) throws SQLException {
        return consultasSelect.estadosProducto();
    }

    public static ObservableList<String> obtenerEstadosList(String RIF) throws SQLException {
        return Converter.resultSetToObservableList(consultasSelect.estadosProducto(), 1);
    }


    //Otros metodos
    public static int totalVentas(String RIF) throws SQLException {
        rs = consultasSelect.totalVentas(RIF);
        rs.next();
        return rs.getInt(1);
    }

    public static int totalProductos(String RIF) throws SQLException {
        rs = consultasSelect.totalProductos(RIF);
        rs.next();
        return rs.getInt(1);
    }

    public static int totalUsuarios(String RIF) throws SQLException {
        rs = consultasSelect.totalUsuarios(RIF);
        rs.next();
        return rs.getInt(1);
    }

    public static ResultSet topÚltimosProductos(String RIF) throws SQLException {
        return consultasSelect.topProductos(RIF, 0);
    }

    public static ResultSet topProductosMasVendidos(String RIF) throws SQLException {
        return consultasSelect.topProductos(RIF, 1);
    }

    public static ObservableList<String> obtenerDepartamentos() throws SQLException {
        return Converter.resultSetToObservableList(consultasSelect.departamentos(), 1);
    }

    public static String[][] ventas7dias(String RIF) throws SQLException {
        rs = consultasSelect.ventas7dias(RIF);
        String[][] ventas;
        ventas = new String[7][2];
        for (int i = 0; i < 7; i++) {
            while (rs.next()) {
                ventas[i][0] = rs.getString(1);
                ventas[i][1] = String.valueOf(rs.getInt(2));
                break;
            }
        }
        return ventas;
    }
}
