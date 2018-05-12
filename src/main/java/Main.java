package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainApplication.fxml"));
        primaryStage.setTitle("Alegere personaj Naruto");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
    }
}
