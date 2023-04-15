package com.example.demo;

import com.example.demo.model.*;
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
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LivreController implements Initializable {
    private Bijou bijou;
    @FXML
    private GridPane gridpane;

    public List<Livre> livres;

    @FXML
    TextField searchBar;

    @FXML
    ScrollPane scrollpane = new ScrollPane();

    @FXML
    void search(ActionEvent event) {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("livres",1,0,searchBar.getText(),"name","ASC");
        afficherTableau(database);
    }

    @FXML
    void sortPriceAsc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("livres",1,1,searchBar.getText(),"price","ASC");
        afficherTableau(database);
    }

    @FXML
    void sortPriceDesc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("livres",1,1,searchBar.getText(),"price","DESC");
        afficherTableau(database);
    }

    @FXML
    void sortNameAsc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("livres",1,1,searchBar.getText(),"title","ASC");
        afficherTableau(database);
    }

    @FXML
    void sortNameDesc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("livres",1,1,searchBar.getText(),"title","DESC");
        afficherTableau(database);
    }

    void afficherTableau(DatabaseModel database)
    {

        livres = database.getLivres();
        gridpane.getChildren().clear();

        int row = 0;
        for (Livre objet : livres) {

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

            // Créez un Label pour la description de l'objet
            Label authorLabel = new Label(objet.getAuthor());
            authorLabel.setWrapText(true);

            Label priceLabel = new Label(String.valueOf(objet.getPrice()));
            Button button2 = new Button();


            button2.setText("Ajouter au panier");
            button2.setStyle("-fx-background-color: #676767;");

            Spinner spinner = new Spinner();
            Spinner<Integer> spinner1 = (Spinner<Integer>) spinner;
            spinner1.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(
                            0,
                            objet.getStockQuantity()
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
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    database.addPanier(objet.getId(), "livres",spinner1.getValue());
                    button2.setText("Accepted " + spinner1.getValue());
                }
            });



            // Incrémentez le numéro de ligne
            row++;
        }
        scrollpane.setContent(gridpane);
    }


    @FXML
    void gotoBijoux(ActionEvent event) throws IOException {
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("bijouPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoDisconnect(ActionEvent event) throws IOException {
        SessionManager.clearSession();
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("ConnexionPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("profilePage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoMenu(ActionEvent event) throws IOException {
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("cataloguePage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoAccess(ActionEvent event) throws IOException {
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("accessPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoBook(ActionEvent event) throws IOException {
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("bookPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoPanier(ActionEvent event) throws IOException {
        // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("panierPage.fxml"));
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
        database.descriptiontabbrutarray("livres",0,0);
        livres = database.getLivres();
        System.out.println(livres.get(0).getTitle());



        int row = 0;
        for (Livre objet : livres) {

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

            // Créez un Label pour la description de l'objet
            Label authorLabel = new Label(objet.getAuthor());
            authorLabel.setWrapText(true);

            Label priceLabel = new Label(String.valueOf(objet.getPrice()));
            Button button2 = new Button();


            button2.setText("Ajouter au panier");
            button2.setStyle("-fx-background-color: #676767;");

            Spinner spinner = new Spinner();
            Spinner<Integer> spinner1 = (Spinner<Integer>) spinner;
            spinner1.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(
                            0,
                            objet.getStockQuantity()
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
                        database.mettreAJourLivresFX(objet.getId(),objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(),objet.getIsbn(), objet.getEnReduction(),objet.getPriceReduc(),objet.getPriceReduc(),(Integer) spinnerAdmin.getValue(),objet.getVenduSansReduc(),objet.getVenduReduc(), objet.getImage());
                        database.descriptiontabbrutarray("livres",0,0);
                        try {
                            gotoBook(e);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                SplitMenuButton splitmenu = new SplitMenuButton();
                MenuItem oui = new MenuItem("Avec réduction");

                oui.setOnAction(e -> {
                    System.out.println("ID du livre : " + objet.getId());
                    System.out.println("Titre du livre : " + objet.getTitle());
                    System.out.println("Auteur du livre : " + objet.getAuthor());
                    System.out.println("Éditeur du livre : " + objet.getPublisher());
                    System.out.println("Date de publication du livre : " + objet.getPublicationDate());
                    System.out.println("ISBN du livre : " + objet.getIsbn());
                    System.out.println("En réduction : " + objet.getEnReduction());
                    System.out.println("Prix réduit du livre : " + objet.getPriceReduc());
                    System.out.println("Prix non réduit du livre : " + objet.getPrice());
                    System.out.println("Quantité en stock du livre : " + objet.getStockQuantity());
                    System.out.println("Nombre de livres vendus sans réduction : " + objet.getVenduSansReduc());
                    System.out.println("Nombre de livres vendus avec réduction : " + objet.getVenduReduc());
                    System.out.println("Image du livre : " + objet.getImage());

                    //database.mettreAJourLivresFX(objet.getId(),objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(),objet.getIsbn(), 1,objet.getPrice(),objet.getPriceReduc(),objet.getStockQuantity(),objet.getVenduSansReduc(),objet.getVenduReduc(), objet.getImage());
                    database.mettreAJourLivresFX(2, "Le Seigneur des Anneaux", "J.R.R. Tolkien", "Houghton Mifflin Harcourt", "1954-1955", "978-2-1234-5678-9", 1, 20.0f, 15.0f, 100, 50, 25, "seigneur_des_anneaux.jpg");
                });
                MenuItem non = new MenuItem("Sans réduction");
                non.setOnAction(e -> {
                    database.mettreAJourLivresFX(objet.getId(), objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(), objet.getIsbn(), 0, objet.getPrice(), objet.getPriceReduc(), objet.getStockQuantity(), objet.getVenduSansReduc(), objet.getVenduReduc(), objet.getImage());
                });
                splitmenu.getItems().addAll(oui,non);
                gridpane.add(splitmenu,8,row);
            }
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    database.addPanier(objet.getId(), "livres",spinner1.getValue());
                    button2.setText("Accepted " + spinner1.getValue());
                }
            });



            // Incrémentez le numéro de ligne
            row++;
        }
        scrollpane.setContent(gridpane);


    }


}
