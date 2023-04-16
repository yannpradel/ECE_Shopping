package com.example.demo.Controller;


import com.example.demo.SessionManager;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur de la vue cataloguePage.fxml.
 * Gère les interactions avec les éléments de l'interface graphique liés au catalogue.
 */
public class CatalogueController{

    int counter = 0;

    VBox vbox1 = new VBox();

    /**
     *Méthode appelée lors du clic sur le bouton qui dirige l'utilisateur vers la page des bijoux.
     *@param event Événement du clic sur le bouton
     *@throws IOException Si une erreur se produit lors du chargement de la page
     */
    @FXML
    void gotoBijoux(ActionEvent event) throws IOException {
        counter++;

        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/bijouPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Change la scène courante vers la page du catalogue principal.
     * @param event L'événement de clic sur le bouton "Menu".
     * @throws IOException Si une erreur se produit lors du chargement de la page.
     */
    @FXML
    void gotoMenu(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/cataloguePage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Cette méthode est appelée lorsque l'utilisateur clique sur un bouton pour accéder à la page de profil.
     * Elle charge le fichier profilePage.fxml à l'aide de la classe FXMLLoader et le définit comme racine de la scène.
     * Ensuite, elle définit la scène sur la scène actuelle de la fenêtre et l'affiche à l'utilisateur.
     * @param event l'événement déclenché par le clic de l'utilisateur sur le bouton
     * @throws IOException si le fichier profilePage.fxml ne peut pas être chargé
     */
    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/profilePage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Déconnexion". Elle permet de déconnecter l'utilisateur en vidant la session en cours, puis de rediriger l'utilisateur vers la page de connexion.
     * @param event un événement ActionEvent
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @FXML
    void gotoDisconnect(ActionEvent event) throws IOException {
        SessionManager.clearSession();
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/ConnexionPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Panier".
     * Charge la page de panier et l'affiche dans la fenêtre en cours.
     * @param event L'événement de clic sur le bouton.
     * @throws IOException Si une erreur survient lors du chargement de la page.
     */
    @FXML
    void gotoPanier(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/panierPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Loads the "accessPage.fxml" file and displays it in the scene
     * @param event The ActionEvent triggered by clicking the "Accessoires" button
     * @throws IOException if the file "accessPage.fxml" cannot be loaded
     */
    @FXML
    void gotoAccess(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/accessPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Book" pour afficher la page de livres.
     * Charge la vue "bookPage.fxml" via FXMLLoader, la définit comme racine de la scène et affiche la scène dans la fenêtre actuelle.
     * @param event L'événement déclencheur de l'action.
     * @throws IOException Si une erreur se produit lors du chargement de la vue "bookPage.fxml".
     */
    @FXML
    void gotoBook(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/bookPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void initialize(URL url, ResourceBundle rb) {
        vbox1.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                try {
                    gotoAccess((ActionEvent) event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
    });





}
}

