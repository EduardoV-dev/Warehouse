package models.DB;
<<<<<<< HEAD
=======

>>>>>>> f225b1f898ceb886d35318745ad4364916395291
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConexionBD {
    /* Objetos para la conexión de la base de datos */
    public static Connection cn;
    private static String USER = "user=sa;";
<<<<<<< HEAD
    private static String PASSWORD = "password=CodingEveryday;";
    private static String databaseName = "databaseName=Warehouse;";
    private static String URL = "jdbc:sqlserver://localhost:1433;"+databaseName+USER+PASSWORD;

    public static Connection conexion(){
        try{
            return DriverManager.getConnection(URL);
        }catch(SQLException e){
=======
    private static String PASSWORD = "password=1234;";
    private static String databaseName = "databaseName=WareHouse;";
    private static String URL = "jdbc:sqlserver://localhost:1433;" + databaseName + USER + PASSWORD;

    public static Connection conexion() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
>>>>>>> f225b1f898ceb886d35318745ad4364916395291
            System.err.println("Error al conectar con la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}
