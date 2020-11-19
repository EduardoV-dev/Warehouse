package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.DB.ConexionBD;
import models.DB.CurrentLogin;
import models.DB.Facade;
import models.POJO.Usuario;
import utils.Validator;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    //Atributos para la funcionalidad de arrastrar la ventana
    private double xOffset = 0;
    private double yOffset = 0;


    @FXML
    private TextField usuarioTF;

    @FXML
    private Button ingresarBtn;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private TextField empresaTF;

    @FXML
    private ImageView resultadoConeccion;

    @FXML
    private Button registrarBtn;

    @FXML
    private Button lockBtn;

    @FXML
    private ImageView lockImg;

    @FXML
    private Button closeBTn;

    //Metodo que se ejecuta al cargar el scene
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Cambiando el icono en caso de que la conexion falle
        if (ConexionBD.conexion() == null) {
            try {
                System.out.println(System.getProperty("user.dir"));
                resultadoConeccion.setImage(new Image(new FileInputStream("src/img/icons/error48r.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //Metodo para cargar el panel de registro
    public void onClickRegistrarse(javafx.event.ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null, "Usuario: admin | Contraseña:12345678");
    }

    //Metodo para ingresar al sistema al dar click en ingresar
    public void onClickIngresar(ActionEvent event) {

        //Metiendo los textfields en un array para validarlos
        TextField[] tfs = {usuarioTF, passwordTF};

        if (Validator.validarTextFields(tfs)) {
            //Instanciando usuario para pasarlo por parametro al Facade
            Usuario user = new Usuario();
            user.setUsuario(usuarioTF.getText());
            user.setContrasena(passwordTF.getText());

            //Intentar ingresar
            try {
                Facade.ingresar(user);
                try {
                    ingresarApp(event);
                } catch (IOException e) {
                    System.out.println("No se encuenta el fxml de la app");
                    e.printStackTrace();
                }
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(null, "Datos no encontrados");
                throwables.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes ingresar todos los datos");
        }
    }

    //Metodo para ingresar al programa
    public void ingresarApp(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene registroScene = new Scene(FXMLLoader.load(getClass().getResource("../views/Home.fxml")));
        stage.setScene(registroScene);
        stage.setMinWidth(1125);
        stage.setMinHeight(700);
        stage.setMaxWidth(1125);
        stage.setMaxHeight(700);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(false);
        stage.show();
    }


    //Metodo para cerrar la aplicación al presionar el boton
    public void onClickCerrar(ActionEvent event) {
        System.exit(0);
    }
}
