package com.example.demo.model;

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
