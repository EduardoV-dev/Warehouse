package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.UConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    Connection con;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Home.fxml"));
        primaryStage.setTitle("Ware House");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(680);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
