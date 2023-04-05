package com.example.demo;

import com.example.demo.model.Produit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CatalogueController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<Produit> productListView;

    int counter = 0;

    @FXML
    private Button bijouxClick;

    @FXML
    void gotoBijoux(ActionEvent event) throws IOException {
        counter++;
       // System.out.println("aaaaa" + counter);
        //welcomeText.setText("Button Clicked " + counter);
        FXMLLoader load = new FXMLLoader(getClass().getResource("productPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Produit testProduit = new Produit("Collier",34.0,"C'est trop cool le collier !");
        // Récupérer la liste de tous les produits depuis la base de données ou autre source de données
        List<Produit> allProducts = new ArrayList<>();

        // Filtrer la liste pour ne conserver que les produits de type "bijou"
        List<Produit> bijoux = allProducts.stream()
                .filter(p -> p.getType().equals("bijou"))
                .collect(Collectors.toList());

        // Afficher les bijoux dans la liste view
        productListView.setItems(FXCollections.observableArrayList(bijoux));

    }
}

