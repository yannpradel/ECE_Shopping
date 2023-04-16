package com.example.demo.Controller;

import com.example.demo.SessionManager;
import com.example.demo.model.Accessoire;
import com.example.demo.model.Bijou;
import com.example.demo.model.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demo.model.DatabaseModel;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Contrôleur de la vue BijouPage.fxml.
 * Gère les interactions avec les éléments de l'interface graphique liés aux bijoux.
 */
public class BijouController implements Initializable {
    private Bijou bijou;
    @FXML
    private GridPane gridpane;

    public List<Bijou> bijoux;

    @FXML TextField searchBar;

    @FXML
    public VBox ventes00 = new VBox();

    @FXML
    public VBox rev = new VBox();

    @FXML
    ScrollPane scrollpane = new ScrollPane();
    /**
     * Fonction de recherche
     * *@param event Événement du clic sur le bouton
     */
    @FXML
    void search(ActionEvent event) {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("bijoux",1,0,searchBar.getText(),"name","ASC");
        afficherTableau(database);
    }
    /**
     * Fonction de recherche avec les prix ascendants
     */
    @FXML
    void sortPriceAsc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("bijoux",1,1,searchBar.getText(),"price","ASC");
        afficherTableau(database);
    }
    /**
     * Fonction de recherche avec les prix descendants
     */
    @FXML
    void sortPriceDesc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("bijoux",1,1,searchBar.getText(),"price","DESC");
        afficherTableau(database);
    }
    /**
     * Fonction de recherche avec les noms ascendants
     */
    @FXML
    void sortNameAsc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("bijoux",1,1,searchBar.getText(),"name","ASC");
        afficherTableau(database);
    }
    /**
     * Fonction de recherche avec les noms descendants
     */
    @FXML
    void sortNameDesc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("bijoux",1,1,searchBar.getText(),"name","DESC");
        afficherTableau(database);
    }
    /**
     * graphe des ventes des bijoux
     */
    @FXML
    void launchStat1()
    {
        DatabaseModel db = new DatabaseModel();
        db.graphventebis("bijoux");
    }
    /**
     * graphe des revenu par ventes des bijoux
     */
    @FXML
    void launchStat2()
    {
        DatabaseModel db = new DatabaseModel();
        db.graphrevenubis("bijoux");
    }
    /**
     * affichage des accessoires
     * @param database variable du modele
     */
    void afficherTableau(DatabaseModel database)
    {

        bijoux = database.getBijoux();
        gridpane.getChildren().clear();

        int row = 0;
        for (Bijou objet : bijoux) {

            // image = new Image(getClass().getResource("/com/example/demo/ab.png").toExternalForm());
            //Image image = new Image("https://www.shutterstock.com/image-vector/open-book-vector-clipart-silhouette-600w-358417976.jpg");
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

            Label priceLabel = new Label(String.valueOf(objet.getPrice()));
            Button button2 = new Button();


            button2.setText("Ajouter au panier");
            button2.setStyle("-fx-background-color: #808080;");
            button2.setStyle("-fx-font-size:12;");

            Spinner spinner = new Spinner();
            Spinner<Integer> spinner1 = (Spinner<Integer>) spinner;
            spinner1.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(
                            0,
                            objet.getStock_quantity()
                    )
            );

            // Ajoutez les éléments à la GridPane
            //gridpane.add(imageView, 0, row);

            gridpane.add(imageView,0,row);
            gridpane.add(nomLabel, 1, row);
            gridpane.add(authorLabel, 2, row);
            gridpane.add(spinner1, 3, row);
            gridpane.add(priceLabel,4,row);
            gridpane.add(button2,5,row);

            if(SessionManager.getLoggedInUser().getIsAdmin()==1)
            {
                Spinner spinnerAdmin = new Spinner();
                Spinner<Integer> spinner2 = (Spinner<Integer>) spinnerAdmin;
                spinner2.setValueFactory(
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(
                                0,
                                100
                        )
                );
                gridpane.add(spinnerAdmin,6,row);
                CheckBox check = new CheckBox();
                gridpane.add(check,7,row);
                check.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        database.mettreAJourBijouxFX(objet.getId(),objet.getName(), objet.getDescription(), objet.getEn_reduction(), objet.getPrice(),objet.getPrice_reduc(), (Integer) spinnerAdmin.getValue(),objet.getVendu_sans_reduc(),objet.getVendu_reduc(),objet.getImage());
                        database.descriptiontabbrutarray("bijoux",0,0);
                        try {
                            gotoBijoux(e);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                SplitMenuButton splitmenu = new SplitMenuButton();
                splitmenu.setText("R");
                splitmenu.setStyle("-fx-font-size:12;");
                MenuItem oui = new MenuItem("Avec réduction");


                oui.setOnAction(e -> {
                    database.mettreAJourBijouxFX(objet.getId(),objet.getName(), objet.getDescription(), 1, objet.getPrice(),objet.getPrice_reduc(), objet.getStock_quantity(),objet.getVendu_sans_reduc(),objet.getVendu_reduc(),objet.getImage());
                    Alert alerte = new Alert(Alert.AlertType.INFORMATION);
                    alerte.setTitle("Ajout de la réduction");
                    alerte.setHeaderText("Réduction !");
                    alerte.setContentText("La réduction a bien été appliquée (lorsqu'un utilisateur achète plus de 4 éléments)");

                    alerte.showAndWait();
                });
                MenuItem non = new MenuItem("Sans réduction");
                non.setOnAction(e -> {
                    database.mettreAJourBijouxFX(objet.getId(),objet.getName(), objet.getDescription(), 0, objet.getPrice(),objet.getPrice_reduc(), objet.getStock_quantity(),objet.getVendu_sans_reduc(),objet.getVendu_reduc(),objet.getImage());
                    Alert alerte = new Alert(Alert.AlertType.INFORMATION);
                    alerte.setTitle("Retrait de la réduction");
                    alerte.setHeaderText("Réduction !");
                    alerte.setContentText("La réduction a bien été supprimé !");

                    alerte.showAndWait();
                });
                splitmenu.getItems().addAll(oui,non);
                gridpane.add(splitmenu,8,row);
            }
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    database.addPanier(objet.getId(), "bijoux",spinner1.getValue());
                    button2.setText("Accepted " + spinner1.getValue());
                }
            });



            // Incrémentez le numéro de ligne
            row++;
        }
        scrollpane.setContent(gridpane);
    }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Compte compte = SessionManager.getLoggedInUser();
        System.out.println("compte.getEmail() : ");
        System.out.println(compte.getFirstName());
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("bijoux",0,0);
        bijoux = database.getBijoux();
        System.out.println(bijoux.get(0).getName());

        ventes00.setVisible(false);
        rev.setVisible(false);

        if(SessionManager.getLoggedInUser().getIsAdmin()==1)
        {
            ventes00.setVisible(true);
            rev.setVisible(true);
            System.out.println("ça cahce");
        }



        int row = 0;
        for (Bijou objet : bijoux) {

            // image = new Image(getClass().getResource("/com/example/demo/ab.png").toExternalForm());
            //Image image = new Image("https://www.shutterstock.com/image-vector/open-book-vector-clipart-silhouette-600w-358417976.jpg");
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

            Label priceLabel = new Label(String.valueOf(objet.getPrice()));
            Button button2 = new Button();


            button2.setText("Ajouter au panier");
            button2.setStyle("-fx-background-color: #808080;");
            button2.setStyle("-fx-font-size:12;");

            Spinner spinner = new Spinner();
            Spinner<Integer> spinner1 = (Spinner<Integer>) spinner;
            spinner1.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(
                            0,
                            objet.getStock_quantity()
                    )
            );

            // Ajoutez les éléments à la GridPane
            //gridpane.add(imageView, 0, row);

            gridpane.add(imageView,0,row);
            gridpane.add(nomLabel, 1, row);
            gridpane.add(authorLabel, 2, row);
            gridpane.add(spinner1, 3, row);
            gridpane.add(priceLabel,4,row);
            gridpane.add(button2,5,row);

            if(SessionManager.getLoggedInUser().getIsAdmin()==1)
            {
                Spinner spinnerAdmin = new Spinner();
                Spinner<Integer> spinner2 = (Spinner<Integer>) spinnerAdmin;
                spinner2.setValueFactory(
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(
                                0,
                                100
                        )
                );
                gridpane.add(spinnerAdmin,6,row);
                CheckBox check = new CheckBox();
                gridpane.add(check,7,row);
                check.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        database.mettreAJourBijouxFX(objet.getId(),objet.getName(), objet.getDescription(), objet.getEn_reduction(), objet.getPrice(),objet.getPrice_reduc(), (Integer) spinnerAdmin.getValue(),objet.getVendu_sans_reduc(),objet.getVendu_reduc(),objet.getImage());
                        database.descriptiontabbrutarray("bijoux",0,0);
                        try {
                            gotoBijoux(e);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                SplitMenuButton splitmenu = new SplitMenuButton();
                splitmenu.setText("R");
                splitmenu.setStyle("-fx-font-size:12;");
                MenuItem oui = new MenuItem("Avec réduction");


                oui.setOnAction(e -> {
                    database.mettreAJourBijouxFX(objet.getId(),objet.getName(), objet.getDescription(), 1, objet.getPrice(),objet.getPrice_reduc(), objet.getStock_quantity(),objet.getVendu_sans_reduc(),objet.getVendu_reduc(),objet.getImage());
                    Alert alerte = new Alert(Alert.AlertType.INFORMATION);
                    alerte.setTitle("Ajout de la réduction");
                    alerte.setHeaderText("Réduction !");
                    alerte.setContentText("La réduction a bien été appliquée (lorsqu'un utilisateur achète plus de 4 éléments)");
                });
                MenuItem non = new MenuItem("Sans réduction");
                non.setOnAction(e -> {
                    database.mettreAJourBijouxFX(objet.getId(),objet.getName(), objet.getDescription(), 0, objet.getPrice(),objet.getPrice_reduc(), objet.getStock_quantity(),objet.getVendu_sans_reduc(),objet.getVendu_reduc(),objet.getImage());
                    Alert alerte = new Alert(Alert.AlertType.INFORMATION);
                    alerte.setTitle("Retrait de la réduction");
                    alerte.setHeaderText("Réduction !");
                    alerte.setContentText("La réduction a bien été supprimé !");
                });
                splitmenu.getItems().addAll(oui,non);
                gridpane.add(splitmenu,8,row);
            }
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    database.addPanier(objet.getId(), "bijoux",spinner1.getValue());
                    button2.setText("Accepted " + spinner1.getValue());
                }
            });



            // Incrémentez le numéro de ligne
            row++;
        }
        scrollpane.setContent(gridpane);


    }


}
