package com.example.demo;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CatalogueController{
    @FXML
    private Label welcomeText;

    int counter = 0;

    @FXML
    private Button bijouxClick;

    @FXML
    void gotoBijoux(ActionEvent event) throws IOException {
        counter++;
       // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("productPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



}

