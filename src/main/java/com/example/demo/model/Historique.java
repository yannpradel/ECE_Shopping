package com.example.demo.model;

import java.sql.Timestamp;
/**
 * Cette classe représente un enregistrement d'historique de vente pour un produit.
 * Elle contient les informations suivantes :
 * - le prénom du client ayant acheté le produit
 * - l'identifiant du produit
 * - le nom de la table de la base de données contenant les informations du produit
 * - la quantité achetée
 * - la date et l'heure de l'achat
 */
public class Historique {

    private String first_name;
    private int product_id;
    private String table_nom;
    private int quantity;
    private Timestamp created_date;

    public String getFirst_name() {
        return first_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTable_nom() {
        return table_nom;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    private int id;
    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private String isbn;
    private int enReduction;
    private double price;
    private double priceReduc;
    private int stockQuantity;
    private int venduSansReduc;
    private int venduReduc;
    private String image;

    // Constructeur avec tous les paramètres
    /**
     * Crée une nouvelle instance de la classe Historique avec les informations fournies.
     *
     * @param id l'identifiant de l'historique
     * @param first_name le nom de la personne liée à l'historique
     * @param product_id l'identifiant du produit lié à l'historique
     * @param table_nom le nom de la table liée à l'historique
     * @param quantity la quantité liée à l'historique
     * @param created_date la date de création de l'historique
     * @param title le titre du produit lié à l'historique
     * @param author l'auteur du produit lié à l'historique
     * @param publisher l'éditeur du produit lié à l'historique
     * @param publicationDate la date de publication du produit lié à l'historique
     * @param isbn l'ISBN du produit lié à l'historique
     * @param enReduction la valeur booléenne indiquant si le produit lié à l'historique est en réduction
     * @param price le prix initial du produit lié à l'historique
     * @param priceReduc le prix réduit du produit lié à l'historique
     * @param stockQuantity la quantité de stock du produit lié à l'historique
     * @param venduSansReduc le nombre de fois où le produit lié à l'historique a été vendu sans réduction
     * @param venduReduc le nombre de fois où le produit lié à l'historique a été vendu en réduction
     * @param image l'URL de l'image du produit lié à l'historique
     */
    public Historique(int id, String first_name, int product_id,String table_nom, int quantity, Timestamp created_date,String title, String author, String publisher, String publicationDate, String isbn, int enReduction,
                  double price, double priceReduc, int stockQuantity, int venduSansReduc, int venduReduc, String image) {
        this.id = id;

        this.first_name=first_name;
        this.product_id=product_id;
        this.table_nom=table_nom;
        this.quantity=quantity;
        this.created_date=created_date;

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.enReduction = enReduction;
        this.price = price;
        this.priceReduc = priceReduc;
        this.stockQuantity = stockQuantity;
        this.venduSansReduc = venduSansReduc;
        this.venduReduc = venduReduc;
        this.image = image;
    }

    // Getters et setters pour toutes les variables d'instance

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEnReduction() {
        return enReduction;
    }

    public void setEnReduction(int enReduction) {
        this.enReduction = enReduction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceReduc() {
        return priceReduc;
    }

    public void setPriceReduc(double priceReduc) {
        this.priceReduc = priceReduc;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getVenduSansReduc() {
        return venduSansReduc;
    }

    public void setVenduSansReduc(int venduSansReduc) {
        this.venduSansReduc = venduSansReduc;
    }

    public int getVenduReduc() {
        return venduReduc;
    }

    public void setVenduReduc(int venduReduc) {
        this.venduReduc = venduReduc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    private String name;
    private String description;

    /**
     * Crée une nouvelle instance de la classe Historique avec les informations fournies.
     *
     * @param id l'identifiant de l'historique
     * @param first_name le nom de la personne liée à l'historique
     * @param product_id l'identifiant du produit lié à l'historique
     * @param table_nom le nom de la table liée à l'historique
     * @param quantity la quantité liée à l'historique
     * @param created_date la date de création de l'historique
     * @param name le nom du produit
     * @param description la description du produit
     * @param en_reduction la valeur booléenne indiquant si le produit lié à l'historique est en réduction
     * @param price le prix initial du produit lié à l'historique
     * @param price_reduc le prix réduit du produit lié à l'historique
     * @param stock_quantity la quantité de stock du produit lié à l'historique
     * @param vendu_sans_reduc le nombre de fois où le produit lié à l'historique a été vendu sans réduction
     * @param vendu_reduc le nombre de fois où le produit lié à l'historique a été vendu en réduction
     * @param image l'URL de l'image du produit lié à l'historique
     */
    public Historique(int id,String first_name,int product_id,String table_nom, int quantity, Timestamp created_date, String name, String description, int en_reduction, double price, double price_reduc, int stock_quantity, int vendu_sans_reduc, int vendu_reduc, String image) {
        this.id = id;

        this.first_name=first_name;
        this.product_id=product_id;
        this.table_nom=table_nom;
        this.quantity=quantity;
        this.created_date=created_date;

        this.name = name;
        this.description = description;
        this.enReduction = en_reduction;
        this.price = price;
        this.priceReduc = price_reduc;
        this.stockQuantity = stock_quantity;
        this.venduSansReduc = vendu_sans_reduc;
        this.venduReduc = vendu_reduc;
        this.image = image;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }
}
