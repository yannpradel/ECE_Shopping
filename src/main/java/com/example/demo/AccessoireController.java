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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccessoireController implements Initializable {
    private Bijou bijou;
    @FXML
    private GridPane gridpane;

    public List<Accessoire> accessoires;

    @FXML
    private TextField searchBar;

    @FXML
    ScrollPane scrollpane = new ScrollPane();



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
    void search(ActionEvent event) {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("accessoires",1,0,searchBar.getText(),"name","ASC");
        afficherTableau(database);
    }
    @FXML
    void sortPriceAsc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("accessoires",1,1,searchBar.getText(),"price","ASC");
        afficherTableau(database);
    }

    @FXML
    void sortPriceDesc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("accessoires",1,1,searchBar.getText(),"price","DESC");
        afficherTableau(database);
    }

    @FXML
    void sortNameAsc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("accessoires",1,1,searchBar.getText(),"name","ASC");
        afficherTableau(database);
    }

    @FXML
    void sortNameDesc()
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("accessoires",1,1,searchBar.getText(),"name","DESC");
        afficherTableau(database);
    }

    void afficherTableau(DatabaseModel database)
    {

        accessoires = database.getAccessoires();
        gridpane.getChildren().clear();

        int row = 0;
        for (Accessoire objet : accessoires) {

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
            button2.setStyle("-fx-background-color: #676767;");

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
                        database.mettreAJourAccessoiresFX(objet.getId(),objet.getName(), objet.getDescription(), objet.getEn_reduction(), objet.getPrice(),objet.getPrice_reduc(), (Integer) spinnerAdmin.getValue(),objet.getVendu_sans_reducl(),objet.getVendu_reduc(),objet.getImage());
                        try {
                            gotoAccess(e);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                SplitMenuButton splitmenu = new SplitMenuButton();
                MenuItem oui = new MenuItem("Avec réduction");
                oui.setOnAction(e -> {
                    database.mettreAJourAccessoiresFX(objet.getId(),objet.getName(), objet.getDescription(), 1, objet.getPrice(),objet.getPrice_reduc(), objet.getStock_quantity(),objet.getVendu_sans_reducl(),objet.getVendu_reduc(),objet.getImage());
                });
                MenuItem non = new MenuItem("Sans réduction");
                oui.setOnAction(e -> {
                    database.mettreAJourAccessoiresFX(objet.getId(),objet.getName(), objet.getDescription(), 0, objet.getPrice(),objet.getPrice_reduc(), objet.getStock_quantity(),objet.getVendu_sans_reducl(),objet.getVendu_reduc(),objet.getImage());
                });
                splitmenu.getItems().addAll(oui,non);
                gridpane.add(splitmenu,8,row);
            }
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    database.addPanier(objet.getId(), "accessoires",spinner1.getValue());
                    button2.setText("Accepted " + spinner1.getValue());
                }
            });



            // Incrémentez le numéro de ligne
            row++;
        }
        scrollpane.setContent(gridpane);


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

    @FXML
    void launchStat1()
    {
        DatabaseModel db = new DatabaseModel();
        db.graphvente("accessoires");
    }

    @FXML
    void launchStat2()
    {
        DatabaseModel db = new DatabaseModel();
        db.graphrevenu("accessoires");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        Compte compte = SessionManager.getLoggedInUser();
        System.out.println("compte.getEmail() : ");
        System.out.println(compte.getFirstName());
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("accessoires",0,0);
        accessoires = database.getAccessoires();
        System.out.println(accessoires.get(0).getName());



        int row = 0;
        for (Accessoire objet : accessoires) {

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
            button2.setStyle("-fx-background-color: #676767;");

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
                        database.mettreAJourAccessoiresFX(objet.getId(),objet.getName(), objet.getDescription(), objet.getEn_reduction(), objet.getPrice(),objet.getPrice_reduc(), (Integer) spinnerAdmin.getValue(),objet.getVendu_sans_reducl(),objet.getVendu_reduc(),objet.getImage());
                        database.descriptiontabbrutarray("accessoires",0,0);
                        try {
                            gotoAccess(e);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                SplitMenuButton splitmenu = new SplitMenuButton();
                MenuItem oui = new MenuItem("Avec réduction");

                oui.setOnAction(e -> {
                    database.mettreAJourAccessoiresFX(objet.getId(),objet.getName(), objet.getDescription(), 1, objet.getPrice(),objet.getPrice_reduc(), objet.getStock_quantity(),objet.getVendu_sans_reducl(),objet.getVendu_reduc(),objet.getImage());

                });
                MenuItem non = new MenuItem("Sans réduction");
                non.setOnAction(e -> {
                    database.mettreAJourAccessoiresFX(objet.getId(),objet.getName(), objet.getDescription(), 0, objet.getPrice(),objet.getPrice_reduc(), objet.getStock_quantity(),objet.getVendu_sans_reducl(),objet.getVendu_reduc(),objet.getImage());
                });
                splitmenu.getItems().addAll(oui,non);
                gridpane.add(splitmenu,8,row);
            }
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    database.addPanier(objet.getId(), "accessoires",spinner1.getValue());
                    button2.setText("Accepted " + spinner1.getValue());
                }
            });



            // Incrémentez le numéro de ligne
            row++;
        }
        scrollpane.setContent(gridpane);


    }


}
