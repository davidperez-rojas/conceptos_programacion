package com.example;

public class Salesman {
    private String documentType;
    private long id;
    private String name;
    private String surname;
    private double totalSales;

    public Salesman(String documentType, long id, String name, String surname) {
        this.documentType = documentType;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.totalSales = 0.0;
    }

    // Method to add sales
    public void addSales(double amount) {
        this.totalSales += amount;
    }

    // Getters
    public String getDocumentType() {
        return documentType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getTotalSales() {
        return totalSales;
    }
}
