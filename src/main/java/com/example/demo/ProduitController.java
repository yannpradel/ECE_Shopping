package com.example.demo;

import com.example.demo.model.Produit;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProduitController implements Initializable {
    private Produit produit;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prixTextField;

    @FXML
    private ListView<Produit> productListView;

    @FXML
    private TextField descriptionTextField;

    public void setProduit(Produit produit) {
        this.produit = produit;
        nomTextField.setText(produit.getNom());
        prixTextField.setText(Double.toString(produit.getPrix()));
        descriptionTextField.setText(produit.getDescription());
    }

    public Produit getProduit() {
        produit.setNom(nomTextField.getText());
        produit.setPrix(Double.parseDouble(prixTextField.getText()));
        produit.setDescription(descriptionTextField.getText());
        return produit;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Produit testProduit = new Produit("Collier",34.0,"C'est trop cool le collier !","bijou");
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
