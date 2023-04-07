package com.example.demo.model;

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
}
