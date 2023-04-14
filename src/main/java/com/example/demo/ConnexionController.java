package com.example.demo;

import com.example.demo.model.Compte;
import com.example.demo.model.DatabaseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class ConnexionController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button connexionButton;

    @FXML
    void connnexionButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        DatabaseModel database = new DatabaseModel();

        if(database.Connexion(username,password) == true)
        {
            //database.effacePanier();
            //Compte compte = new Compte(username,password,database.getAdminS());
            //email = SQL GET EMAIL FROM USERNAME AND PASSWORD
            Compte compte = new Compte(username,password);
            SessionManager.setLoggedInUser(compte);
            System.out.println(SessionManager.getLoggedInUser().getEmail());
            FXMLLoader load = new FXMLLoader(getClass().getResource("cataloguePage.fxml"));
            Parent root = load.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}

