package models.DB;


import data.DatosEmpresa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.POJO.*;
import utils.Converter;

import models.POJO.Producto;
import models.POJO.Usuario;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Facade {
    private static ResultSet rs;

    /************************************************ NOTA *********************************************/
    // El uso de throws se debe a que se pueda controlar los errores relacionados con que
    // un registro ya exista, de esta manera se puede personalizar la visualización de un error
    // más facilmente


    //Metodos para el login
    public static boolean ingresar(Usuario usuario) throws SQLException {
        System.out.println(usuario.getUsuario());
        rs = consultasSelect.ingresar(usuario);
        boolean existe = rs.next();
        CurrentLogin.setCurrentUsuario(new Usuario(rs.getString(1), usuario.getContrasena(), rs.getString(2)));
        return existe;
    }

    //Metodos CRUD Usuarios

    public static boolean agregarUsuario(Usuario usuario) throws SQLException {
        if (consultasInsert.crearUsuario(usuario) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static ResultSet obtenerUsuarios() throws SQLException {
        return consultasSelect.informacionUsuarios(1, "");
    }

    public static ResultSet obtenerUsuariosFiltrados(String usuario) throws SQLException {
        return consultasSelect.informacionUsuarios(0, usuario);
    }

    public static boolean modificarUsuario(Usuario usuario) throws SQLException {
        if (consultasUpdate.actualizarUsuario(usuario) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean eliminarUsuario(String id) throws SQLException {
        if (consultasDelete.eliminarUsuario(id) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static ObservableList<String> obtenerRoles() {
        ObservableList<String> roles = FXCollections.observableArrayList();
        roles.add("Moderador");
        roles.add("Empleado");
        return roles;
    }


    //Metodos CRUD Productos


    public static boolean agregarProducto(Producto p, String nombreProveedor, String usuario) throws SQLException {
        if (consultasInsert.agregarProducto(p, nombreProveedor, usuario) == 1) {
            return true;
        } else {
            return false;
        }
    }


    public static ResultSet obtenerProductos() throws SQLException {
        return consultasSelect.informacionProductos(1, "");
    }

    public static ResultSet filtrarProductos(String producto) throws SQLException {
        return consultasSelect.informacionProductos(0, producto);
    }

    public static boolean modificarProducto(Producto producto) throws SQLException {
        return consultasUpdate.actualizarProducto(producto) == 1;
    }

    public static boolean eliminarProducto(String idProducto) throws SQLException {
        return consultasDelete.eliminarProducto(idProducto) == 1;
    }


    //Metodos CRUD Entradas

    public static boolean agregarEntrada(EntradaSalida entrada, String proveedor, String usuario) throws SQLException {
        return consultasInsert.realizarIngresoProducto(entrada, proveedor, usuario) == 1;
    }


    //Metodos CRUD Salidas

    public static boolean agregarSalida(EntradaSalida salida, Usuario usuario) throws SQLException {
        return consultasInsert.realizarVentaProducto(salida, usuario.getUsuario()) == 1;
    }


    //Metodos CRUD Proveedores

    public static boolean agregarProveedor(Proveedor p, String usuario) throws SQLException {
        return consultasInsert.agregarProveedor(p, usuario) == 1;
    }

    public static ResultSet obtenerProveedores() throws SQLException {
        return consultasSelect.informacionProveedores(1, "");
    }

    public static ObservableList<String> obtenerProveedoresList() throws SQLException {
        return Converter.resultSetToObservableList(consultasSelect.nombresProveedores(), 1);
    }

    public static boolean actualizarProveedor(Proveedor p) throws SQLException {
        return consultasUpdate.actualizarProveedor(p) == 1;
    }

    public static boolean eliminarProveedor(String id) throws SQLException {
        return consultasDelete.eliminarProveedor(id) == 1;
    }

    public static Proveedor obtenerProveedor(String nombreProveedor) throws SQLException {
        rs = consultasSelect.informacionProveedores(0, nombreProveedor);
        Proveedor p = new Proveedor();
        while (rs.next()) {
            p.setIdProveedor(rs.getString(1));
            p.setNombres(rs.getString(2));
            p.setApellidos(rs.getString(3));
            p.setCorreo(rs.getString(4));
            p.setTelefono(rs.getString(5));
        }
        return p;
    }

    //Metodos CRUD Medida

    public static boolean agregarMedida(String medida) throws SQLException {
        return consultasInsert.nuevaMedida(medida) == 1;
    }

    public static ResultSet obtenerMedidas() throws SQLException {
        return consultasSelect.medidasProducto();
    }

    public static ObservableList<String> obtenerMedidasList() throws SQLException {
        return Converter.resultSetToObservableList(consultasSelect.medidasProducto(), 1);
    }


    //Metodos CRUD estado

    public static boolean agregarEstado(String estado) throws SQLException {
        return consultasInsert.nuevoEstado(estado) == 1;
    }

    public static ResultSet obtenerEstados() throws SQLException {
        return consultasSelect.estadosProducto();
    }

    public static ObservableList<String> obtenerEstadosList() throws SQLException {
        return Converter.resultSetToObservableList(consultasSelect.estadosProducto(), 1);
    }


    //Otros metodos
    public static int totalVentas() throws SQLException {
        rs = consultasSelect.totalVentas();
        rs.next();
        return rs.getInt(1);
    }

    public static int totalProductos() throws SQLException {
        rs = consultasSelect.totalProductos();
        rs.next();
        return rs.getInt(1);
    }

    public static int totalUsuarios() throws SQLException {
        rs = consultasSelect.totalUsuarios();
        rs.next();
        return rs.getInt(1);
    }

    public static ResultSet topÚltimosProductos() throws SQLException {
        return consultasSelect.topProductos(0);
    }

    public static ResultSet topProductosMasVendidos() throws SQLException {
        return consultasSelect.topProductos(1);
    }

    /* TODO
    public static ObservableList<String> obtenerDepartamentos() throws SQLException {
        return Converter.resultSetToObservableList(consultasSelect.d(), 1);
    }
     */


    // Obtiene las ventas realizadas en los ultimos 7 dias
    // Devuelve una matriz de 7 filas y 2 columnas, el indice 0 de las columnas
    // devuelve la fecha, mientras que el indice 1 devuelve el total de ventas
    public static String[][] ventas7dias() throws SQLException {
        rs = consultasSelect.ventas7dias();
        int filas = 0;
        while (rs.next()) filas++;
        rs = consultasSelect.ventas7dias();
        String[][] ventas;
        ventas = new String[filas][2];

        for (int i = 0; i < 7; i++) {
            while (rs.next()) {
                ventas[i][0] = rs.getString(1);
                ventas[i][1] = String.valueOf(rs.getInt(2));
                break;
            }
        }
        return ventas;
    }


    public static Empresa obtenerDatosEmpresa() throws SQLException {
        return DatosEmpresa.obtenerDatosEmpresa();
    }
}
