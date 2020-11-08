package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Atributos para la funcionalidad de arrastrar la ventana
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button btnAjustes;

    @FXML
    private AnchorPane productosPane;

    @FXML
    private Button btnClose;

    @FXML
    private AnchorPane analisisPane;

    @FXML
    private Button btnProductos;

    @FXML
    private AnchorPane ajustesPane;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private AnchorPane usuariosPane;

    @FXML
    private AnchorPane ventaPane;

    @FXML
    private TableView<?> ultimosProductosVendidosTV;

    @FXML
    private AnchorPane northPane;

    @FXML
    private Label ventasTotalesLabel;

    @FXML
    private LineChart<?, ?> actividadesVentaLineChart;

    @FXML
    private TableView<?> productosMasVendidosTV;

    @FXML
    private Button btnVenta;

    @FXML
    private Button btnAnalisis;

    @FXML
    private AnchorPane inicioPane;

    @FXML
    private Button btnInicio;

    @FXML
    private Label usuariosRegistradosLabel;

    @FXML
    private Label productosTotalesLabel;

    @FXML
    private Button btnUsuarios;


    //Metodo que se ejecuta al cargar el scene
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Panel de Inicio
    }

    //Metodo para actualizar los datos generales de la ventana de inicio
    public void actualizarDatosGenerales() {
        
    }


    //Funcionalidad de la navegación
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
            ajustesPane.toFront();
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

        for (Button button : menuBotones) {
            if (button == b) {
                button.setStyle("-fx-background-color: rgba(0,0,0,.15)");
            } else {
                button.setStyle("-fx-background-color: transparent");
            }
        }
        menuBotones.clear();
    }

    //Funcionalidad para arrastrar la ventana
    public void onMouseDragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void onMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    //Metodo para cerrar la aplicación al presionar el boton
    public void closeApp(ActionEvent e) {
        System.exit(0);
    }
}