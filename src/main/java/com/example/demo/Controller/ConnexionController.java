package com.example.demo.Controller;

import com.example.demo.SessionManager;
import com.example.demo.model.Compte;
import com.example.demo.model.DatabaseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Contrôleur de la vue connexionPage.fxml.
 * Gère les interactions avec les éléments de l'interface graphique liés à la connexion.
 */
public class ConnexionController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    /**
     * Gère l'événement du clic sur le bouton de connexion.
     * Récupère les valeurs des champs nom d'utilisateur et mot de passe et les utilise pour se connecter à la base de données.
     * Si la connexion est réussie, initialise un objet Compte avec les informations de connexion, stocke l'utilisateur connecté dans la session et affiche la page du catalogue.
     * @param event l'événement du clic sur le bouton de connexion.
     * @throws IOException si la ressource FXML du cataloguePage.fxml n'a pas pu être chargée.
     */
    @FXML
    void connnexionButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        DatabaseModel database = new DatabaseModel();

        if(database.Connexion(username,password) == true)
        {

            Compte compte = new Compte(username,password);
            SessionManager.setLoggedInUser(compte);
            System.out.println(SessionManager.getLoggedInUser().getEmail());
            FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/cataloguePage.fxml"));
            Parent root = load.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}

