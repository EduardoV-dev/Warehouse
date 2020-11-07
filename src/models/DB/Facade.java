package models.DB;
import models.DB.*;
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

    public static void main(String[] args) {
        // Datos falsos de prueba
        Empresa empresa1 = new Empresa("ABC", "EmpresaABC", "sample@sample.com", "Street", "88008800", "Managua");
        Usuario usuario1 = new Usuario("usuarioGen", "1234");
        String RIF = empresa1.getRIF();

        // Ingresar empresa y usuario admin
        /*try{
            registrarse(empresa1, usuario1);
            System.out.println("Exito");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("La empresa ya existe");
        }*/

        // Obtener informacion de un usuario de una empresa
        try{
            rs = ingresar(empresa1, usuario1);
            System.out.println("Registro existente - Se puede logear");
            while(rs.next()) System.out.println("Usuario: "+rs.getString(1)+"\nContraseña: "+rs.getString(2)+"\nRIF: "+rs.getString(3));
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Registro inexistente - No se puede logear");
        }
    }
}
