package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CatalogueController implements Initializable {
    @FXML
    private Label welcomeText;

    int counter = 0;

    @FXML
    private Button bijouxClick;

    @FXML
    void gotoBijoux(ActionEvent event) {
        counter++;
        System.out.println("aaaaa" + counter);
        welcomeText.setText("Button Clicked " + counter);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

