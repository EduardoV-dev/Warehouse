package models.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.DB.*;
import models.POJO.Empresa;
import models.POJO.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Facade {
    private static ResultSet rs;
    private static Connection con;

    /************************************************ NOTA *********************************************/
    // El uso de throws se debe a que se pueda controlar los errores relacionados con que
    // un registro ya exista, de esta manera se puede personalizar la visualización de un error
    // más facilmente

    // Metodo usado para registrar una nueva empresa y un nuevo usuario
    // con el rol de administrador
    public static boolean registrarse(Empresa empresa, Usuario usuario) throws SQLException {
        // Devuelve true o false dependiendo de si el registro fue realizado
        // correctamente o no
        return LogInSignUp.registrarse(empresa, usuario);
    }

    // Metodo usado para obtener la informacion de login necesaria para poder
    // Ingresar al sistema de una empresa - Este metodo devuelve el usuario y contraseña
    // filtrado por el numero RIF en un resultSet

    // Devuelve el total de ventas registradas en una empresa
    // Devuelve -> int TotalVentas
    public static ResultSet totalVentas(String RIF) throws SQLException {
        return consultasSelect.totalVentas(RIF);
    }

    // Devuelve el total de productos registrados en una empresa
    // Devuelve -> int TotalProductos
    public static ResultSet totalProductos(String RIF) throws SQLException {
        return consultasSelect.totalProductos(RIF);
    }

    // Devuelve el total de usuarios registrados en una empresa
    // Devuelve -> int TotalUsuarios
    public static ResultSet totalUsuarios(String RIF) throws SQLException {
        return consultasSelect.totalUsuarios(RIF);
    }

    // Devuelve el top 5 de los últimos productos vendidos en una empresa
    // Devuelve -> String nombreProducto
    public static ResultSet topÚltimosProductos(String RIF) throws SQLException {
        return consultasSelect.topProductos(RIF, 0);
    }

    // Devuelve el top 5 de los productos más vendidos en una empresa
    // Devuelve -> String nombreProducto, int cantidadTotalVendidaDelProducto
    public static ResultSet topProductosMasVendidos(String RIF) throws SQLException {
        return consultasSelect.topProductos(RIF, 1);
    }

    /*public static void main(String[] args) {

    }*/

    /*
     *   Metodo para obtener los departamentos
     *   retorna un ObservableList de Strings con el nombre de los departamentos
     */
    /*public static ObservableList<String> obtenerDepartamentos() {
        ObservableList<String> lista = FXCollections.observableArrayList();
        try {
            con = ConexionBD.conexion();
            Statement st = con.createStatement();
            String sql = "SELECT departamento FROM adm.Departamento";
            rs = st.executeQuery(sql);

            //creando y llenando la lista
            while (rs.next()) {
                lista.add(rs.getString(1).trim());
            }

            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }*/
}
