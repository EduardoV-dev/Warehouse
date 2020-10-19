package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.UConnection;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    Connection con;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Home.fxml"));
        primaryStage.setTitle("Ware House");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1125);
        primaryStage.setMinHeight(700);
        primaryStage.setMaximized(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
