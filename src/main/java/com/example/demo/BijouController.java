package com.example.demo;

import com.example.demo.model.Bijou;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BijouController implements Initializable {
    private Bijou bijou;
    @FXML
    private GridPane gridpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("a.jpg");
        Image image = new Image(file.toURI().toString());
        Bijou[] objets = {
                new Bijou("Collier", 34.0, "C'est trop cool le collier !",image),
                new Bijou("Bracelet", 34.0, "Nickel mon gars !",image),
                new Bijou("Boucle d'oreilles", 34.0, "Pas mal !",image)
                //new Bijou("Collier", 34.0, "C'est trop cool le collier !",new Image("https://cdn.pixabay.com/photo/2023/03/22/20/16/muffin-7870491_960_720.jpg")),
               // new Bijou("Collier", 34.0, "C'est trop cool le collier !",new Image("https://cdn.pixabay.com/photo/2023/03/22/20/16/muffin-7870491_960_720.jpg")),

        };

        int row = 0;
        for (Bijou objet : objets) {

            // Créez un ImageView pour l'image de l'objet
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            // Créez un Label pour le nom de l'objet
            Label nomLabel = new Label(objet.getBijouName());

            // Créez un Label pour la description de l'objet
            Label descriptionLabel = new Label(objet.getBijouDescription());
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
