package controllers;

import data.EmpresaNombre;
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

    //Atributo para la funcionalidad del candado
    boolean bloqueado;

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

        //Funcionalidad del texfield con el candado
        try {
            EmpresaNombre.crearArchivo(new File("bname.dat"));
            if (EmpresaNombre.obtenerNombre() != "") {
                empresaTF.setText(EmpresaNombre.obtenerNombre());
                empresaTF.setEditable(false);
                bloquear();

            } else {
                desbloquear();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para cargar el panel de registro
    public void onClickRegistrarse(javafx.event.ActionEvent actionEvent) {
        if (ConexionBD.conexion() != null) {
            try {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                Scene registroScene = new Scene(FXMLLoader.load(getClass().getResource("../views/Register.fxml")));
                stage.setScene(registroScene);
                stage.setMinWidth(850);
                stage.setMinHeight(518);
                stage.setMaxWidth(850);
                stage.setMaxHeight(518);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Metodo para ingresar al sistema al dar click en ingresar
    public void onClickIngresar(ActionEvent event) {

        //Metiendo los textfields en un array para validarlos
        TextField[] tfs = {usuarioTF, passwordTF, empresaTF};

        if (Validator.validarTextFields(tfs)) {
            //Instanciando usuario para pasarlo por parametro al Facade
            Usuario user = new Usuario();
            user.setUsuario(usuarioTF.getText());
            user.setContrasena(passwordTF.getText());

            //Intentar ingresar
            try {
                Facade.ingresar(empresaTF.getText(), user);
                JOptionPane.showMessageDialog(null, "Has ingresado con exito");
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
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(false);
        stage.show();
    }


    //Metodos para la funcionalidad del candado
    public void toggleLock(ActionEvent actionEvent) {
        //System.out.println(bloqueado);
        if (bloqueado) {
            desbloquear();
        } else {
            bloquear();
        }
    }

    public void bloquear() {
        try {
            EmpresaNombre.crearArchivo(new File("bname.dat"));
            EmpresaNombre.establecerNombre(empresaTF.getText().trim(), true);

            lockImg.setImage(new Image(new FileInputStream("src/img/icons/lock24g.png")));
            empresaTF.setEditable(false);
            empresaTF.setStyle("-fx-background-color:#ddd; -fx-background-radius: 5px;");
            bloqueado = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void desbloquear() {
        try {
            EmpresaNombre.crearArchivo(new File("bname.dat"));
            EmpresaNombre.establecerNombre(empresaTF.getText().trim(), false);
            lockImg.setImage(new Image(new FileInputStream("src/img/icons/unlock24g.png")));
            empresaTF.setEditable(true);
            empresaTF.setStyle("-fx-background-color:#FFF; -fx-background-radius: 5px;");
            bloqueado = false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para cerrar la aplicación al presionar el boton
    public void onClickCerrar(ActionEvent event) {
        System.exit(0);
    }
}
