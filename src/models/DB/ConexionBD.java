package models.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    /* Objetos para la conexión de la base de datos */
    public static Connection cn;
    private static String USER = "user=Warehouse;";
    private static String PASSWORD = "password=1234;";
    private static String databaseName = "databaseName=Warehouse;";
    private static String URL = "jdbc:sqlserver://localhost:1433;" + databaseName + USER + PASSWORD;

    public static Connection conexion(){
        try{
            return DriverManager.getConnection(URL);
        }catch(SQLException e){
            System.err.println("Error al conectar con la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}
