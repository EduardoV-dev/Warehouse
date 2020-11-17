package models.DB;

import models.POJO.Producto;
import models.POJO.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Facade {
    public static ResultSet rs;

    /************************************************ NOTA *********************************************/
    // El uso de throws se debe a que se pueda controlar los errores relacionados con que
    // un registro ya exista, de esta manera se puede personalizar la visualización de un error
    // más facilmente


    // Metodo usado para obtener la informacion de login necesaria para poder
    // Ingresar al sistema de una empresa - Este metodo devuelve el usuario y contraseña
    public static ResultSet ingresar(Usuario usuario) throws SQLException {
        return consultasSelect.ingresar(usuario);
    }

    // Devuelve el total de ventas registradas
    // Devuelve -> int TotalVentas
    public static ResultSet totalVentas(String RIF) throws SQLException {
        return consultasSelect.totalVentas();
    }

    // Devuelve el total de productos registrados
    // Devuelve -> int TotalProductos
    public static ResultSet totalProductos() throws SQLException {
        return consultasSelect.totalProductos();
    }

    // Devuelve el total de usuarios registrados
    // Devuelve -> int TotalUsuarios
    public static ResultSet totalUsuarios() throws SQLException {
        return consultasSelect.totalUsuarios();
    }

    // Devuelve el top 5 de los últimos productos vendidos
    // Devuelve -> String Nombre
    public static ResultSet topÚltimosProductos() throws SQLException {
        return consultasSelect.topProductos(0);
    }

    // Devuelve el top 5 de los productos más vendidos
    // Devuelve -> String Nombre, int Ventas
    public static ResultSet topProductosMasVendidos() throws SQLException {
        return consultasSelect.topProductos(1);
    }

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

    public static void main(String[] args) {
        try {
            rs = consultasSelect.ingresar(new Usuario("admin", "EduardoVarela"));
            if (rs.next()) {
                System.out.println("Dentro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
