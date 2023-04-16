package com.example.demo.Controller;

import com.example.demo.SessionManager;
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
import javafx.scene.layout.VBox;
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
    public VBox ventes00 = new VBox();

    @FXML
    public VBox rev = new VBox();

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

    @FXML
    void launchStat1()
    {
        DatabaseModel db = new DatabaseModel();
        db.graphvente("livres");
    }

    @FXML
    void launchStat2()
    {
        DatabaseModel db = new DatabaseModel();
        db.graphrevenubis("livres");
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
            button2.setStyle("-fx-background-color: #808080;");
            button2.setStyle("-fx-font-size:12;");

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
                        database.mettreAJourLivresFX(objet.getId(),objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(),objet.getIsbn(), objet.getEnReduction(),(float)objet.getPrice(),(float)objet.getPriceReduc(),(Integer) spinnerAdmin.getValue(),objet.getVenduSansReduc(),objet.getVenduReduc(), objet.getImage());
                        database.descriptiontabbrutarray("livres",0,0);
                        try {
                            gotoBook(e);
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


                    database.mettreAJourLivresFX(objet.getId(), objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(),objet.getIsbn(), 1,(float)objet.getPrice(),(float)objet.getPriceReduc(),objet.getStockQuantity(),objet.getVenduSansReduc(),objet.getVenduReduc(), objet.getImage());
                    Alert alerte = new Alert(Alert.AlertType.INFORMATION);
                    alerte.setTitle("Ajout de la réduction");
                    alerte.setHeaderText("Réduction !");
                    alerte.setContentText("La réduction a bien été appliquée (lorsqu'un utilisateur achète plus de 4 éléments)");//database.mettreAJourLivresFX(2, "Le Seigneur des Anneaux", "J.R.R. Tolkien", "Houghton Mifflin Harcourt", "1954-1955", "978-2-1234-5678-9", 1, 20.0f, 15.0f, 100, 50, 25, "seigneur_des_anneaux.jpg");
                    //database.descriptiontabbrutarray("livres",0,0);
                    //database.mettreAJourLivresFX(livres.get(0).getId(), livres.get(2).getTitle(), livres.get(2).getAuthor(), livres.get(2).getPublisher(), livres.get(2).getPublicationDate(), livres.get(2).getIsbn(), livres.get(2).getEnReduction(),(float)livres.get(2).getPrice(), (float)livres.get(2).getPriceReduc(), livres.get(2).getStockQuantity(), livres.get(2).getVenduSansReduc(), livres.get(2).getVenduReduc(), livres.get(2).getImage());
                });
                MenuItem non = new MenuItem("Sans réduction");
                non.setOnAction(e -> {
                    database.mettreAJourLivresFX(objet.getId(),objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(),objet.getIsbn(), 0,(float)objet.getPrice(),(float)objet.getPriceReduc(),objet.getStockQuantity(),objet.getVenduSansReduc(),objet.getVenduReduc(), objet.getImage());
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
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/bijouPage.fxml"));
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
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/ConnexionPage.fxml"));
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
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/profilePage.fxml"));
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
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/cataloguePage.fxml"));
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
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/accessPage.fxml"));
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
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/bookPage.fxml"));
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
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/panierPage.fxml"));
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

        ventes00.setVisible(false);
        rev.setVisible(false);

        if(SessionManager.getLoggedInUser().getIsAdmin()==1)
        {
            ventes00.setVisible(true);
            rev.setVisible(true);
            System.out.println("ça cahce");
        }



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
            button2.setStyle("-fx-background-color: #808080;");
            button2.setStyle("-fx-font-size:12;");

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
                        database.mettreAJourLivresFX(objet.getId(),objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(),objet.getIsbn(), objet.getEnReduction(),(float)objet.getPrice(),(float)objet.getPriceReduc(),(Integer) spinnerAdmin.getValue(),objet.getVenduSansReduc(),objet.getVenduReduc(), objet.getImage());
                        database.descriptiontabbrutarray("livres",0,0);
                        try {
                            gotoBook(e);
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


                    database.mettreAJourLivresFX(objet.getId(), objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(),objet.getIsbn(), 1,(float)objet.getPrice(),(float)objet.getPriceReduc(),objet.getStockQuantity(),objet.getVenduSansReduc(),objet.getVenduReduc(), objet.getImage());
                    Alert alerte = new Alert(Alert.AlertType.INFORMATION);
                    alerte.setTitle("Ajout de la réduction");
                    alerte.setHeaderText("Réduction !");
                    alerte.setContentText("La réduction a bien été appliquée (lorsqu'un utilisateur achète plus de 4 éléments)");//database.mettreAJourLivresFX(2, "Le Seigneur des Anneaux", "J.R.R. Tolkien", "Houghton Mifflin Harcourt", "1954-1955", "978-2-1234-5678-9", 1, 20.0f, 15.0f, 100, 50, 25, "seigneur_des_anneaux.jpg");
                    //database.descriptiontabbrutarray("livres",0,0);
                    //database.mettreAJourLivresFX(livres.get(0).getId(), livres.get(2).getTitle(), livres.get(2).getAuthor(), livres.get(2).getPublisher(), livres.get(2).getPublicationDate(), livres.get(2).getIsbn(), livres.get(2).getEnReduction(),(float)livres.get(2).getPrice(), (float)livres.get(2).getPriceReduc(), livres.get(2).getStockQuantity(), livres.get(2).getVenduSansReduc(), livres.get(2).getVenduReduc(), livres.get(2).getImage());
                });
                MenuItem non = new MenuItem("Sans réduction");
                non.setOnAction(e -> {
                    database.mettreAJourLivresFX(objet.getId(),objet.getTitle(), objet.getAuthor(), objet.getPublisher(), objet.getPublicationDate(),objet.getIsbn(), 0,(float)objet.getPrice(),(float)objet.getPriceReduc(),objet.getStockQuantity(),objet.getVenduSansReduc(),objet.getVenduReduc(), objet.getImage());
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
