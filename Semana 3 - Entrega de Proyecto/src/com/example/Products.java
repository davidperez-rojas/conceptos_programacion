package com.example;

public class Products {
    private String name;
    private double price;
    private int quantitySold;

    public Products(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantitySold = 0;
    }

    // Method to add the quantity sold
    public void addQuantitySold(int quantity) {
        this.quantitySold += quantity;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantitySold() {
        return quantitySold;
    }
}
