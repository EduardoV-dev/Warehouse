package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Button btnAjustes;

    @FXML
    private BorderPane homePane;

    @FXML
    private Button btnProductos;

    @FXML
    private VBox menuContenedor;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnCompras;

    @FXML
    private BorderPane productsPane;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnVentas;


    public void handleMenuButton(ActionEvent e) {
        if (e.getSource() == btnInicio) {
            setActive(btnInicio);
            homePane.toFront();
        }
        if (e.getSource() == btnProductos) {
            setActive(btnProductos);
            productsPane.toFront();
        }
    }

    private void setActive(Button b) {
        ArrayList<Button> menuBotones = new ArrayList<>();
        menuBotones.add(btnInicio);
        menuBotones.add(btnVentas);
        menuBotones.add(btnCompras);
        menuBotones.add(btnProductos);
        menuBotones.add(btnAjustes);
        menuBotones.add(btnUsuarios);

        for (Button button : menuBotones) {
            if (button == b) {
                button.setStyle("-fx-background-color: #085F63");
            } else {
                button.setStyle("-fx-background-color: #054A4D;");
            }
        }
        menuBotones.clear();
    }
}

