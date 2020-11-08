package models.DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consultasSelect {
    private static Connection cn;
    private static CallableStatement cs;

    public static ResultSet totalVentas(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalVentas(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

    public static ResultSet totalProductos(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalProductos(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

    public static ResultSet totalUsuarios(String RIF) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTotalUsuarios(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

    // Opc = 0; obtiene el top 5 de los últimos productos vendidos
    // Opc = 1; obtiene el top 5 de los productos más vendidos
    public static ResultSet topProductos(String RIF, int opc) throws SQLException {
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selTopProductos(?,?)}");
        cs.setString(1, RIF);
        cs.setInt(2, opc);
        return cs.executeQuery();
    }

    public static ResultSet departamentos() throws SQLException{
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selDepartamentos}");
        return cs.executeQuery();
    }

    // Obtiene las ventas realizadas en los últimos 7 días
    public static ResultSet ventas7dias(String RIF) throws SQLException{
        cn = ConexionBD.conexion();
        cs = cn.prepareCall("{call selVentasSiete(?)}");
        cs.setString(1, RIF);
        return cs.executeQuery();
    }

}
