package com.example.demo;

import com.example.demo.model.Accessoire;
import com.example.demo.model.Bijou;
import com.example.demo.model.DatabaseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        FXMLLoader load = new FXMLLoader(getClass().getResource("hello-view.fxml"));
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



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseModel database = new DatabaseModel();
        database.createDatabase();
        database.descriptiontabbrutarray("accessoires",0,0);
        accessoires = database.getAccessoires();
        System.out.println(accessoires.get(0).getName());
        File file = new File("a.jpg");
        Image image = new Image(file.toURI().toString());

        int row = 0;
        for (Accessoire objet : accessoires) {

            // Créez un ImageView pour l'image de l'objet
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            // Créez un Label pour le nom de l'objet
            Label nomLabel = new Label(objet.getName());

            // Créez un Label pour la description de l'objet
            Label descriptionLabel = new Label(objet.getDescription());
            descriptionLabel.setWrapText(true);

            // Ajoutez les éléments à la GridPane
            gridpane.add(imageView, 0, row);
            gridpane.add(nomLabel, 1, row);
            gridpane.add(descriptionLabel, 2, row);



            // Incrémentez le numéro de ligne
            row++;
        }
    }


}
