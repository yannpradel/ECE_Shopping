package com.example.demo.model;
/**
 * Cette classe représente un livre vendu sur le site de vente en ligne.
 * Elle contient toutes les informations relatives à ce livre, y compris son nom,
 * sa description, son prix, sa quantité en stock et son image.
 */
public class Livre {
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
     * Crée une nouvelle instance de la classe Livre avec les informations fournies.
     *
     * @param id l'identifiant de ce livre
     * @param title le titre du produit lié à ce livre
     * @param author l'auteur du produit lié à ce livre
     * @param publisher l'éditeur du produit lié à ce livre
     * @param publicationDate la date de publication du produit lié à ce livre
     * @param isbn l'ISBN du produit lié à ce livre
     * @param enReduction la valeur booléenne indiquant si le produit lié à ce livre est en réduction
     * @param price le prix initial du produit lié à ce livre
     * @param priceReduc le prix réduit du produit lié à ce livre
     * @param stockQuantity la quantité de stock du produit lié à ce livre
     * @param venduSansReduc le nombre de fois où le produit lié à ce livre a été vendu sans réduction
     * @param venduReduc le nombre de fois où le produit lié à ce livre a été vendu en réduction
     * @param image l'URL de l'image du produit lié à ce livre
     */
    public Livre(int id, String title, String author, String publisher, String publicationDate, String isbn, int enReduction,
                 double price, double priceReduc, int stockQuantity, int venduSansReduc, int venduReduc, String image) {
        this.id = id;
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


    public String getPublisher() {
        return publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }


    public String getIsbn() {
        return isbn;
    }


    public int getEnReduction() {
        return enReduction;
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

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getVenduSansReduc() {
        return venduSansReduc;
    }

    public int getVenduReduc() {
        return venduReduc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
