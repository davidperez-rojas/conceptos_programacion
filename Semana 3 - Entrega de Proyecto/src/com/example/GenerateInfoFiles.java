package com.example;

import java.io.*;
import java.util.*;

public class GenerateInfoFiles {

    private static final Random RANDOM = new Random();

    // Method to create the products file
    public void createProductsFile(int numberOfProducts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
            for (int i = 1; i <= numberOfProducts; i++) {
                String productName = "Product_" + i;
                double price = RANDOM.nextDouble() * 100;  // Generates a random price between 0 and 100
                writer.write(i + ";" + productName + ";" + price + "\n");
            }
            System.out.println("File products.txt created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating the file products.txt: " + e.getMessage());
        }
    }

    // Method to create the salesmen info file
    public void createSalesManInfoFile(int numberOfSalesmen) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("salesmen_info.txt"))) {
            for (int i = 1; i <= numberOfSalesmen; i++) {
                String name = "Salesman_" + i;
                writer.write("CC;" + (1000 + i) + ";" + name + ";Surname_" + i + "\n");
            }
            System.out.println("File salesmen_info.txt created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating the file salesmen_info.txt: " + e.getMessage());
        }
    }

    // Method to create the sales files for the salesmen
    public void createSalesMenFile(int salesmanId, String name, long sales) {
        String fileName = "Sales_" + name.replace(" ", "") + "_" + salesmanId + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 1; i <= 3; i++) {
                writer.write(RANDOM.nextInt(50) + ";" + RANDOM.nextInt(20) + "\n"); // Random product
            }
            System.out.println("File " + fileName + " created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating the sales file for " + name + ": " + e.getMessage());
        }
    }

    // Method to read the salesmen info file
    public List<Salesman> readSalesmen(String fileName) throws IOException {
        List<Salesman> salesmen = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                salesmen.add(new Salesman(parts[0], Long.parseLong(parts[1]), parts[2], parts[3]));
            }
        }
        return salesmen;
    }

    // Method to read the products file
    public Map<Integer, Products> readProducts(String fileName) throws IOException {
        Map<Integer, Products> products = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                products.put(Integer.parseInt(parts[0]), new Products(parts[1], Double.parseDouble(parts[2])));
            }
        }
        return products;
    }

    // Process the sales from the sales files
    public void processSales(List<Salesman> salesmen, Map<Integer, Products> products) throws IOException {
        for (Salesman salesman : salesmen) {
            // Ensure the same file name is used
            String salesFileName = "Sales_" + salesman.getName().replace(" ", "") + "_" + salesman.getId() + ".txt";
            try (BufferedReader br = new BufferedReader(new FileReader(salesFileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    int productId = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);

                    if (products.containsKey(productId)) {
                        Products product = products.get(productId);
                        double total = product.getPrice() * quantity;
                        salesman.addSales(total);
                        product.addQuantitySold(quantity);
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error: File " + salesFileName + " not found");
            }
        }
    }

    // Generate the salesmen report file
    public void generateSalesmenReport(List<Salesman> salesmen, String fileName) throws IOException {
        salesmen.sort(Comparator.comparing(Salesman::getTotalSales).reversed());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Salesman salesman : salesmen) {
                writer.write(salesman.getName() + " " + salesman.getSurname() + ";" + salesman.getTotalSales() + "\n");
            }
        }
        System.out.println("Salesmen report generated successfully.");
    }

    // Generate the products report file
    public void generateProductsReport(Map<Integer, Products> products, String fileName) throws IOException {
        List<Products> productList = new ArrayList<>(products.values());
        productList.sort(Comparator.comparing(Products::getQuantitySold).reversed());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Products product : productList) {
                writer.write(product.getName() + ";" + product.getQuantitySold() + ";" + product.getPrice() + "\n");
            }
        }
        System.out.println("Products report generated successfully.");
    }
}
