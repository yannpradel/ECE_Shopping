package com.example.demo.model;

import javafx.scene.image.Image;
/**
 * Cette classe représente un bijou vendu sur le site de vente en ligne.
 * Elle contient toutes les informations relatives à ce bijou, y compris son nom,
 * sa description, son prix, sa quantité en stock et son image.
 */
public class Bijou {
    private int id;
    private String name;
    private String description;
    private int en_reduction;
    private double price;
    private double price_reduc;
    private int stock_quantity;
    private int vendu_sans_reduc;
    private int vendu_reduc;
    private String image;
    /**
     * Construit un objet Bijou avec toutes ses informations.
     *
     * @param id L'identifiant du bijou.
     * @param name Le nom du bijou.
     * @param description La description du bijou.
     * @param en_reduction L'état de réduction du bijou (0 = pas en réduction, 1 = en réduction).
     * @param price Le prix du bijou.
     * @param price_reduc Le prix réduit du bijou.
     * @param stock_quantity La quantité en stock du bijou.
     * @param vendu_sans_reduc La quantité vendue sans réduction.
     * @param vendu_reduc La quantité vendue avec réduction.
     * @param image L'image du bijou.
     */
    public Bijou(int id, String name, String description, int en_reduction, double price,
                      double price_reduc, int stock_quantity, int vendu_sans_reduc, int vendu_reduc, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.en_reduction = en_reduction;
        this.price = price;
        this.price_reduc = price_reduc;
        this.stock_quantity = stock_quantity;
        this.vendu_sans_reduc = vendu_sans_reduc;
        this.vendu_reduc = vendu_reduc;
        this.image = image;
    }
    /**
     * Construit un objet Bijou avec les informations minimales nécessaires.
     *
     * @param name Le nom du bijou.
     * @param price Le prix du bijou.
     * @param description La description du bijou.
     * @param image L'image du bijou.
     */
    public Bijou(String name, double price, String description, String image) {
        this.name=name;
        this.price=price;
        this.description=description;
        this.image=image;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getEn_reduction() {
        return en_reduction;
    }

    public double getPrice() {
        return price;
    }

    public double getPrice_reduc() {
        return price_reduc;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public int getVendu_sans_reduc() {
        return vendu_sans_reduc;
    }

    public int getVendu_reduc() {
        return vendu_reduc;
    }

    public String getImage() {
        return image;
    }

}
