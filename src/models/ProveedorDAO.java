package models;

import utils.UConnection;

import java.sql.*;
import java.util.ArrayList;

public class ProveedorDAO {


    public boolean agregarProveedor(String nombre, String telefono, String email) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql;
        try {
            con = UConnection.getConnection();
            sql = "INSERT INTO proveedores VALUES ?, ?, ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, telefono);
            statement.setString(3, email);
            statement.execute();
            return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<ProveedorDTO> obtenerProveedores() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        String sql;
        ArrayList<ProveedorDTO> lista = new ArrayList<>();

        try {
            con = UConnection.getConnection();
            sql = "SELECT id_proveedor, nombre, telefono, email FROM proveedores";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            ProveedorDTO p;

            while (rs.next()) {
                p = new ProveedorDTO();
                p.setId_proveedor(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setTelefono(rs.getString(3));
                p.setEmail(rs.getString(4));

                lista.add(p);
            }
            return lista;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }


}
