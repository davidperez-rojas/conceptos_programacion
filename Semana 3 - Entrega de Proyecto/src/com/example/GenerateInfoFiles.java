package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    private static final Random RANDOM = new Random();

    // Method to create a sales file for a vendor with pseudo-random sales data
    public static void createSalesMenFile(int salesCount, String name, long id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Sales_" + name + "_" + id + ".txt"))) {
            // Write sales data in the required format
            for (int i = 0; i < salesCount; i++) {
                int productId = RANDOM.nextInt(1000) + 1; // Random product ID between 1 and 1000
                int quantity = RANDOM.nextInt(10) + 1;   // Random quantity between 1 and 10
                writer.write(String.format("%d;%d;%n", productId, quantity));
            }
            System.out.println("Sales file created: Sales_" + name + "_" + id + ".txt");
        } catch (IOException e) {
            System.err.println("Error creating sales file: " + e.getMessage());
        }
    }

    // Method to create a products file with pseudo-random product data
    public static void createProductsFile(int productsCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
            for (int i = 1; i <= productsCount; i++) {
                String productName = "Product" + i;
                double price = RANDOM.nextDouble() * 100; // Random price between 0 and 100
                writer.write(String.format("%d;%s;%.2f%n", i, productName, price));
            }
            System.out.println("Products file created: products.txt");
        } catch (IOException e) {
            System.err.println("Error creating products file: " + e.getMessage());
        }
    }

    // Method to create a salesmen info file with pseudo-random vendor data
    public static void createSalesManInfoFile(int salesmanCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("salesmen_info.txt"))) {
            for (int i = 1; i <= salesmanCount; i++) {
                String name = "Salesman" + i;
                String surname = "Surname" + i;
                writer.write(String.format("ID%d;12345678;%s;%s%n", i, name, surname));
            }
            System.out.println("Salesmen info file created: salesmen_info.txt");
        } catch (IOException e) {
            System.err.println("Error creating salesmen info file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Generate files with pseudo-random data
        createProductsFile(10); // Example: create a file with 10 products
        createSalesManInfoFile(5); // Example: create a file with 5 salesmen
        createSalesMenFile(5, "JohnDoe", 1L); // Example: create a file for one salesman with 5 sales

        // You can generate additional salesmen files as needed
        createSalesMenFile(3, "JaneSmith", 2L);
        createSalesMenFile(4, "AliceBrown", 3L);
    }
}