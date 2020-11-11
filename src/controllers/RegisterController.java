package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.DB.ConexionBD;
import models.DB.Facade;
import models.POJO.Empresa;
import models.POJO.Usuario;
import utils.Fuller;
import utils.Validator;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    Connection con;

    @FXML
    private TextField correoEmpresaTF;

    @FXML
    private TextArea empresaDireccionTA;

    @FXML
    private ComboBox<String> empresaDepartamentoCB = new ComboBox<>();

    @FXML
    private TextField empresaRIFTF;

    @FXML
    private PasswordField confirmarPassUsuarioTF;

    @FXML
    private TextField empresaNombreTF;

    @FXML
    private TextField empresaTelefonoTF;

    @FXML
    private PasswordField passUsuarioTF;

    @FXML
    private Button cancelarBtn;

    @FXML
    private Button registrarBtn;

    @FXML
    private TextField usuarioNombreTF;

    //Metodo que se ejecuta al cargar el scene
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //cargar departamentos en un arraylist
        ObservableList<String> departamentos;
        try {
            departamentos = Facade.obtenerDepartamentos();
            //System.out.println(departamentos);
            empresaDepartamentoCB.setPromptText("Selecciona un departamento:");
            empresaDepartamentoCB.setItems(departamentos);
        } catch (SQLException throwables) {
            System.out.println("Error al obtener departamentos");
            throwables.printStackTrace();
        }
    }

    //Metodos para las acciones de los botones cancelar y registrar
    public void onClickBtn(ActionEvent e) {

        if (e.getSource() == cancelarBtn) {
            Scene registroScene = null;
            try {
                Node node = (Node) e.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                registroScene = new Scene(FXMLLoader.load(getClass().getResource("../views/Login.fxml")));
                stage.setScene(registroScene);
                stage.setMaxWidth(800);
                stage.setMaxHeight(600);
                stage.setMinWidth(800);
                stage.setMinHeight(600);
                stage.show();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        if (e.getSource() == registrarBtn) {

            TextField[] tfs = {empresaNombreTF, empresaRIFTF, empresaTelefonoTF, correoEmpresaTF,
                    usuarioNombreTF, passUsuarioTF, confirmarPassUsuarioTF};

            ComboBox[] cbs = {empresaDepartamentoCB};

            TextArea[] tas = {empresaDireccionTA};

            if (!Validator.validarTextFields(tfs) || !Validator.validarComboBoxs(cbs)
                    || !Validator.validarTextAreas(tas)) {
                JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
            } else {
                //en caso de que los datos esten validados realizar la conexion

                //crear objeto empresa que se pasará como argumento
                Empresa empresa = new Empresa();
                empresa.setNombre(empresaNombreTF.getText().trim());
                empresa.setCorreo(correoEmpresaTF.getText().trim());
                empresa.setTelefono(empresaTelefonoTF.getText().trim());
                empresa.setRIF(empresaRIFTF.getText().trim());
                empresa.setDepartamento(empresaDepartamentoCB.getValue());
                empresa.setDireccion(empresaDireccionTA.getText().trim());

                //crear objeto usuario que se pasará como argumento
                Usuario usuario = new Usuario();
                usuario.setUsuario(usuarioNombreTF.getText());
                usuario.setContrasena(passUsuarioTF.getText());
                usuario.setRol("Administrador"); //Siempre moderador el primer usuario

                //ejecutando el procedimiento en la base de datos
                try {
                    Facade.registrarse(empresa, usuario);
                    JOptionPane.showMessageDialog(null, "Empresa registrada con exito!");

                    Node node = (Node) e.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene registroScene = new Scene(FXMLLoader.load(getClass().getResource("../views/Login.fxml")));
                    stage.setScene(registroScene);
                    stage.setMinWidth(800);
                    stage.setMinHeight(600);
                    stage.setMaxWidth(800);
                    stage.setMaxHeight(600);
                    stage.show();
                } catch (SQLException | IOException throwables) {
                    JOptionPane.showMessageDialog(null, "Ya existe una empresa con ese nombre");
                    throwables.printStackTrace();
                }
            }

        }
    }

}

