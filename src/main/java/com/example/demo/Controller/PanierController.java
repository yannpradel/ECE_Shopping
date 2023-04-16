package com.example.demo.Controller;

import com.example.demo.SessionManager;
import com.example.demo.model.*;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Contrôleur de la vue panierPage.fxml.
 * Gère les interactions avec les éléments de l'interface graphique liés au panier.
 */
public class PanierController implements Initializable {
    private Bijou bijou;
    @FXML
    private GridPane gridpane;

    public List<Panier> paniers;

    @FXML
    private Text usertext;

    @FXML
    private Text usertextBalance;



    @FXML
    ScrollPane scrollpane = new ScrollPane();
    /**
     *Méthode appelée lors du clic sur le bouton qui dirige l'utilisateur vers la page des bijoux.
     *@param event Événement du clic sur le bouton
     *@throws IOException Si une erreur se produit lors du chargement de la page
     */
    @FXML
    void gotoBijoux(ActionEvent event) throws IOException {
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/bijouPage.fxml"));
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
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/ConnexionPage.fxml"));
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
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/cataloguePage.fxml"));
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
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
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
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/bookPage.fxml"));
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
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/profilePage.fxml"));
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
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/panierPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Cette méthode est appelée lorsque l'utilisateur clique sur le bouton "Confirmer" pour finaliser un achat.
     * Elle met à jour la balance de l'utilisateur connecté dans la base de données et dans la session courante.
     * Elle charge la page du panier (panierPage.fxml) à l'aide de la classe FXMLLoader et la définit comme racine de la scène.
     * Ensuite, elle définit la scène sur la fenêtre principale de l'application et l'affiche à l'utilisateur.
     * @param event l'événement déclenché par le clic de l'utilisateur sur le bouton "Confirmer"
     * @throws IOException si la page panierPage.fxml ne peut pas être chargée
     */
    @FXML
    private void onConfirmButtonClicked(ActionEvent event) throws IOException{
        DatabaseModel database = new DatabaseModel();
        int balance = database.ConfirmationAchatpage(SessionManager.getLoggedInUser().getFirstName());
        SessionManager.getLoggedInUser().setBalance(balance);
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/panierPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initialise la vue du panier. Cette méthode récupère le compte de l'utilisateur connecté, affiche le nom et le solde de l'utilisateur,
     * et récupère les articles du panier de la base de données. Ensuite, elle affiche les articles dans une grille avec leur image, nom, description,
     * quantité, prix et un bouton de suppression pour chaque article. Si l'article est un accessoire ou un bijou, l'image affichée est l'image de l'article.
     * Si l'article est un livre, l'image affichée est une image par défaut pour les livres.
     * @param url L'URL de la vue du panier.
     * @param rb Les ressources pour la vue du panier.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Compte compte = SessionManager.getLoggedInUser();
        System.out.println("ON EST DANS LE PANIER : ");
        System.out.println(compte.getFirstName());
        DatabaseModel database = new DatabaseModel();
        database.afficherPaniertabbrutarray();
        paniers = database.getPaniers();
        System.out.println(paniers.size());

        usertext.setText(SessionManager.getLoggedInUser().getFirstName());
        usertextBalance.setText(String.valueOf(SessionManager.getLoggedInUser().getBalance()));


        int row = 0;
        for (Panier objet : paniers) {

            System.out.println(paniers.size());

            if(objet.getTable_nom().equals("accessoires") || objet.getTable_nom().equals("bijoux"))
            {
                System.out.println("L'objet : " + row + "est un accessoire ou un bijou");
                Image image = new Image(objet.getImage());

                // Créez un ImageView pour l'image de l'objet
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);

                // Créez un Label pour le nom de l'objet
                Label nomLabel = new Label(objet.getName());

                // Créez un Label pour la description de l'objet
                Label authorLabel = new Label(objet.getDescription());
                authorLabel.setWrapText(true);

                // Créez un Label pour le nom de l'objet
                Label quantLabel = new Label(String.valueOf(objet.getQuantity()));

                Label priceLabel = new Label(String.valueOf(objet.getPrice()));
                Button button2 = new Button();


                button2.setText("Suprimmer");
                button2.setStyle("-fx-background-color: #676767;");

                // Ajoutez les éléments à la GridPane
                //gridpane.add(imageView, 0, row);

                gridpane.add(imageView,0,row);
                gridpane.add(nomLabel, 1, row);
                gridpane.add(authorLabel, 2, row);
                gridpane.add(priceLabel,3,row);
                gridpane.add(quantLabel,4,row);
                gridpane.add(button2,5,row);
                button2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        database.effacePanieruniqueitem(objet.getId());
                        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/panierPage.fxml"));
                        Parent root = null;
                        try {
                            root = load.load();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                    }
                });
            }

            if(objet.getTable_nom().equals("livres")) {
                System.out.println("L'objet : " + row + "est un livre");
                // image = new Image(getClass().getResource("/com/example/demo/ab.png").toExternalForm());
                //Image image = new Image("https://www.shutterstock.com/image-vector/open-book-vector-clipart-silhouette-600w-358417976.jpg");
                Image image = new Image(objet.getImage());

                // Créez un ImageView pour l'image de l'objet
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);

                // Créez un Label pour le nom de l'objet
                Label nomLabel = new Label(objet.getTitle());

                // Créez un Label pour le nom de l'objet
                Label quantLabel = new Label(String.valueOf(objet.getQuantity()));

                // Créez un Label pour la description de l'objet
                Label authorLabel = new Label(objet.getAuthor());
                authorLabel.setWrapText(true);

                Label priceLabel = new Label(String.valueOf(objet.getPrice()));
                Button button2 = new Button();


                button2.setText("Suprimmer");
                button2.setStyle("-fx-background-color: #676767;");

                // Ajoutez les éléments à la GridPane
                //gridpane.add(imageView, 0, row);

                gridpane.add(imageView, 0, row);
                gridpane.add(nomLabel, 1, row);
                gridpane.add(authorLabel, 2, row);
                gridpane.add(priceLabel, 3, row);
                gridpane.add(quantLabel,4,row);
                gridpane.add(button2, 5, row);
                button2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        database.effacePanieruniqueitem(objet.getId());
                        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/panierPage.fxml"));
                        Parent root = null;
                        try {
                            root = load.load();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                    }
                });
            }



            // Incrémentez le numéro de ligne
            row++;
        }
        scrollpane.setContent(gridpane);


    }


}
