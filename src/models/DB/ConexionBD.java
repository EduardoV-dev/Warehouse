package models.DB;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConexionBD {
    /* Objetos para la conexión de la base de datos */
    public static Connection cn;
    private static String USER = "user=sa;";
    private static String PASSWORD = "password=CodingEveryday;";
    private static String databaseName = "databaseName=Warehouse;";
    private static String URL = "jdbc:sqlserver://localhost:1433;"+databaseName+USER+PASSWORD;

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
