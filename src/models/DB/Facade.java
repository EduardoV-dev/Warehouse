package models.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.POJO.Empresa;
import models.POJO.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Facade {
    public static ResultSet rs;

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
    public static ResultSet ingresar(Empresa empresa, Usuario usuario) throws SQLException {
        return LogInSignUp.ingresar(empresa, usuario);
    }

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

    /*public static void main(String[] args) {
        try{
            obtenerDepartamentos().forEach(item -> {
                System.out.println(item);
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
}
