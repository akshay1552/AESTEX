package com.example.myapplication;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String barcode;
    private String name;
    private String price;
    private String description;
    private String imageUrl;
    private int quantity;

    public Product() {
        // This is the no-argument constructor
    }

    public Product(String barcode, String name, String imageUrl, String price, String description, int quantity) {
        this.barcode = barcode;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(String id, String barcode, String name, String price, String description, String imageUrl, int quantity) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
