package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {

    @FXML
    private Button start;

    @FXML
    private void changeToMainScreen(ActionEvent event) {
        try {
            Parent mainScreen = FXMLLoader.load(getClass().getResource("../../resources/layout/mainApplication.fxml"));
            Scene mainScreenScene = new Scene(mainScreen);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainScreenScene);
            window.setResizable(false);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
