package com.example.demo;

import com.example.demo.model.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProduitController {
    private Produit produit;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prixTextField;

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

}
