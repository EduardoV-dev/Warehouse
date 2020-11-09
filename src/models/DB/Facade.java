package models.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.POJO.Empresa;
import models.POJO.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Facade {
    private static ResultSet rs;

    /************************************************ NOTA *********************************************/
    // El uso de throws se debe a que se pueda controlar los errores relacionados con que
    // un registro ya exista, de esta manera se puede personalizar la visualización de un error
    // más facilmente

    /*
     *   Metodo para registrar una empresa junto con su primer usuario
     *   -Retorna un booleano
     *    True en caso de que el registro sea exitoso
     *    False en caso de que la empresa ya exista o haya un problema en la conexion
     */
    public static boolean registrarse(Empresa empresa, Usuario usuario) throws SQLException {
        return LogInSignUp.registrarse(empresa, usuario);
    }

    /*
     *   Metodo para ingresarse en el sistema
     *   -Retorna un booleano
     *    True en caso de que las credenciales sean correctas
     *    False en caso de que las credenciales sean incorrectas
     */
    public static boolean ingresar(String nombreEmpresa, Usuario usuario) throws SQLException {
        return LogInSignUp.ingresar(nombreEmpresa, usuario);
    }

    /*
     *   Metodo para obtener el total de ventas de la empresa
     *   Retorna la cantidad de ventas totales
     */
    public static int totalVentas(String RIF) throws SQLException {
        rs = consultasSelect.totalVentas(RIF);
        rs.next();
        return rs.getInt(1);
    }

    /*
     *   Metodo para obtener el total de productos de la empresa
     *   Retorna la cantidad de productos
     */
    public static int totalProductos(String RIF) throws SQLException {
        rs = consultasSelect.totalProductos(RIF);
        rs.next();
        return rs.getInt(1);
    }

    /*
     *   Metodo para obtener el total de usuarios de la empresa
     *   Retorna la cantidad de usuarios
     */
    public static int totalUsuarios(String RIF) throws SQLException {
        rs = consultasSelect.totalUsuarios(RIF);
        rs.next();
        return rs.getInt(1);
    }

    // Devuelve el top 5 de los últimos productos vendidos en una empresa
    // Devuelve -> String Nombre
    public static ResultSet topÚltimosProductos(String RIF) throws SQLException {
        return consultasSelect.topProductos(RIF, 0);
    }

    // Devuelve el top 5 de los productos más vendidos en una empresa
    // Devuelve -> String Nombre, int Ventas
    public static ResultSet topProductosMasVendidos(String RIF) throws SQLException {
        return consultasSelect.topProductos(RIF, 1);
    }

    // Devuelve todos los departamentos existentes
    // Devuelve -> ObservableArrayList departamentos
    public static ObservableList<String> obtenerDepartamentos() throws SQLException {
        ObservableList<String> departamentos = FXCollections.observableArrayList();
        rs = consultasSelect.departamentos();
        //creando y llenando la lista
        while (rs.next()) {
            departamentos.add(rs.getString(1).trim());
        }
        return departamentos;
    }

    // Obtiene las ventas realizadas en los ultimos 7 dias
    // Devuelve una matriz de 7 filas y 2 columnas, el indice 0 de las columnas
    // devuelve la fecha, mientras que el indice 1 devuelve el total de ventas
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
