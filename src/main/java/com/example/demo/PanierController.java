package com.example.demo;

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

    @FXML
    private void onConfirmButtonClicked(ActionEvent event) throws IOException{
        DatabaseModel database = new DatabaseModel();
        int balance = database.ConfirmationAchatpage(SessionManager.getLoggedInUser().getFirstName());
        SessionManager.getLoggedInUser().setBalance(balance);
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
                        FXMLLoader load = new FXMLLoader(getClass().getResource("panierPage.fxml"));
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
                        FXMLLoader load = new FXMLLoader(getClass().getResource("panierPage.fxml"));
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
