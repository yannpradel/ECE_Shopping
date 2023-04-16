package com.example.demo.model;
/**
 * Cette classe représente un accessoire vendu sur le site de vente en ligne.
 * Elle contient toutes les informations relatives à cet accessoire, y compris son nom,
 * sa description, son prix, sa quantité en stock et son image.
 */
public class Accessoire {
    /**
     * L'identifiant de l'accessoire.
     */
    private int id;
    /**
     * Le nom de l'accessoire.
     */
    private String name;
    /**
     * La description de l'accessoire.
     */
    private String description;
    /**
     * Indique si l'accessoire est en promotion si 1 sinon 0.
     */
    private int en_reduction;
    /**
     * Le prix de l'accessoire sans promotion.
     */
    private double price;
    /**
     * Le prix de l'accessoire avec promotion.
     */
    private double price_reduc;
    /**
     * La quantité d'accessoires en stock.
     */
    private int stock_quantity;
    /**
     * Le nombre d'accessoires vendus sans promotion.
     */
    private int vendu_sans_reduc;
    /**
     * Le nombre d'accessoires vendus avec promotion.
     */
    private int vendu_reduc;
    /**
     * Le nom de l'image associée à l'accessoire.
     */
    private String image;

    /**
     * Constructeur de la classe Accessoire.
     *
     * @param id l'identifiant de l'accessoire
     * @param name le nom de l'accessoire
     * @param description la description de l'accessoire
     * @param en_reduction indique si l'accessoire est en promotion ou non
     * @param price le prix de l'accessoire sans promotion
     * @param price_reduc le prix de l'accessoire avec promotion
     * @param stock_quantity la quantité d'accessoires en stock
     * @param vendu_sans_reduc le nombre d'accessoires vendus sans promotion
     * @param vendu_reduc le nombre d'accessoires vendus avec promotion
     * @param image le nom de l'image associée à l'accessoire
     */
    public Accessoire(int id, String name, String description, int en_reduction, double price,
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
     * Retourne l'identifiant de l'accessoire.
     *
     * @return l'identifiant de l'accessoire
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne l'identifiant de Name.
     *
     * @return le nom de l'accessoir
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne l'identifiant de Description.
     *
     * @return la description de l'accessoir
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne l'identifiant de En_reduction.
     *
     * @return si l'accessoir est en reduction
     */
    public int getEn_reduction() {
        return en_reduction;
    }

    /**
     * Retourne l'identifiant de Price.
     *
     * @return le prix sans reduction de l'accessoir
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retourne l'identifiant de Price_reduc.
     *
     * @return le prix avec reduction de l'accessoir
     */
    public double getPrice_reduc() {
        return price_reduc;
    }

    /**
     * Retourne l'identifiant de Stock_quantity.
     *
     * @return la quantite restante en stock
     */
    public int getStock_quantity() {
        return stock_quantity;
    }

    /**
     * Retourne l'identifiant de Vendu_sans_reducl.
     *
     * @return le nombre d'accessoirs vendu sans reduction
     */
    public int getVendu_sans_reducl() {
        return vendu_sans_reduc;
    }

    /**
     * Retourne l'identifiant de Vendu_reduc.
     *
     * @return le nombre d'accessoirs vendu avec reduction
     */
    public int getVendu_reduc() {
        return vendu_reduc;
    }

    /**
     * Retourne l'identifiant de Image.
     *
     * @return l'image de l'accessoir
     */
    public String getImage() {
        return image;
    }


}