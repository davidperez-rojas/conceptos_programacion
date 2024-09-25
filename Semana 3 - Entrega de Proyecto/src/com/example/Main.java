package com.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create an instance of GenerateInfoFiles
        GenerateInfoFiles generator = new GenerateInfoFiles();

        // Step 1: Create the products.txt file
        generator.createProductsFile(50);  // Change 50 to the number of products needed

        // Step 2: Create the salesmen_info.txt file for 50 salesmen
        generator.createSalesManInfoFile(50);  // Change 50 to the number of salesmen

        // Step 3: Read the salesmen from the file and create sales files for each one
        try {
            List<Salesman> salesmen = generator.readSalesmen("salesmen_info.txt");

            // Create sales files for all the salesmen read
            for (Salesman salesman : salesmen) {
                generator.createSalesMenFile((int) salesman.getId(), salesman.getName(), 5000L);
            }

            // Read the products
            Map<Integer, Products> products = generator.readProducts("products.txt");

            // Process the sales
            generator.processSales(salesmen, products);

            // Generate reports
            generator.generateSalesmenReport(salesmen, "salesmen_report.csv");
            generator.generateProductsReport(products, "products_report.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
