package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UConnection {
    private static Connection con;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/warehouse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "");
            System.out.println("Se coneto con exito!");
            return con;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}
