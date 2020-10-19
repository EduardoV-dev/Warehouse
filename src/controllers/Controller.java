package controllers;

import com.sun.org.apache.bcel.internal.generic.I2F;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class Controller {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnAjustes;

    @FXML
    private Button btnProveedores;

    @FXML
    private AnchorPane homePane;

    @FXML
    private Button btnVenta;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnAnalisis;

    @FXML
    private Button btnInicio;

    @FXML
    private HBox panelSuperior;

    @FXML
    private VBox panelMenu;

    @FXML
    private StackPane panelCentral;

    @FXML
    private Button btnUsuarios;

    @FXML
    private AnchorPane productosPane;

    @FXML
    private AnchorPane analisisPane;

    @FXML
    private AnchorPane ajustesPane;

    @FXML
    private AnchorPane usuariosPane;

    @FXML
    private AnchorPane ventaPane;

    @FXML
    private AnchorPane proveedoresPane;

    @FXML
    private AnchorPane inicioPane;

    public void closeApp(ActionEvent e) {
        System.exit(0);
    }

    public void handleMenuButton(ActionEvent e) {
        if (e.getSource() == btnInicio) {
            setActive(btnInicio);
            inicioPane.toFront();
        }

        if (e.getSource() == btnVenta) {
            setActive(btnVenta);
            ventaPane.toFront();
        }

        if (e.getSource() == btnProductos) {
            setActive(btnProductos);
            productosPane.toFront();
        }

        if (e.getSource() == btnProveedores) {
            setActive(btnProveedores);
            proveedoresPane.toFront();
        }

        if (e.getSource() == btnUsuarios) {
            setActive(btnUsuarios);
            usuariosPane.toFront();
        }

        if (e.getSource() == btnAnalisis) {
            setActive(btnAnalisis);
            analisisPane.toFront();
        }

        if (e.getSource() == btnAjustes) {
            setActive(btnAjustes);
            btnAjustes.toFront();
        }
    }


    private void setActive(Button b) {
        ArrayList<Button> menuBotones = new ArrayList<>();
        menuBotones.add(btnInicio);
        menuBotones.add(btnVenta);
        menuBotones.add(btnAnalisis);
        menuBotones.add(btnProductos);
        menuBotones.add(btnAjustes);
        menuBotones.add(btnUsuarios);
        menuBotones.add(btnProveedores);

        for (Button button : menuBotones) {
            if (button == b) {
                button.setStyle("-fx-background-color: rgba(0,0,0,.15)");
            } else {
                button.setStyle("-fx-background-color: transparent;");
            }
        }
        menuBotones.clear();
    }

}

