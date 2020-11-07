package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.DB.ConexionBD;


import java.sql.Connection;


public class Main extends Application {
    //private double x, y;
    private double xOffset = 0;
    private double yOffset = 0;

    Connection con;

    @Override
    public void start(Stage stage) throws Exception {
        System.setProperty("prism.lcdtext", "false");

        con = ConexionBD.conexion();
        if (con != null) {
            System.out.println("CONECTADO");
        } else {
            System.out.println("NO CONECTADO");
        }

        /*
        Parent root = FXMLLoader.load(getClass().getResource("../views/Home.fxml"));
        primaryStage.setTitle("Ware House");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(700);
        primaryStage.setMaximized(false);
        //we gonna drag the frame
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.show();
         */

        Parent root = FXMLLoader.load(getClass().getResource("../views/Login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setMaxWidth(800);
        stage.setMaxHeight(600);
        stage.setTitle("Login WareHouse");


        //grab your root here
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
