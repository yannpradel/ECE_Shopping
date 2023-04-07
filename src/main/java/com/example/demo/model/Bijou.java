package com.example.demo.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bijou {
    private String bijouName;
    private Double bijouPrice;
    private String bijouDescription;
    private Image bijouPic;

    public Bijou(String bijouName, Double bijouPrice, String bijouDescription, Image bijouPic) {
        this.bijouName = bijouName;
        this.bijouPrice = bijouPrice;
        this.bijouDescription = bijouDescription;
        this.bijouPic = bijouPic;
    }

    public Bijou(String bijouName, Double bijouPrice) {
        this.bijouName = bijouName;
        this.bijouPrice = bijouPrice;
    }

    public String getBijouName() {
        return bijouName;
    }

    public Double getBijouPrice() {
        return bijouPrice;
    }

    public String getBijouDescription() {
        return bijouDescription;
    }

    public Image getImage() {
        return bijouPic;
    }
}
