package file_handling.streamprocessingmultigbcsv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class LargeCsvGenerator {
    public static void main(String[] args) throws IOException {
//        String fileName = "bigfile.csv";
        Path fileName = Paths.get("C:\\Users\\DELL\\Desktop\\New folder\\bigfile.csv");
        long targetSize = 2L * 1024 * 1024 * 1024; // 3 GB
        Random random = new Random();
        String[] categories = {"Fruit", "Vegetable", "Meat", "Dairy"};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.toFile()), 50 * 1024 * 1024)) {
            writer.write("product,category,quantity,price");
            writer.newLine();

            long bytesWritten = 0;
            int counter = 0;

            while (bytesWritten < targetSize) {
                String product = "Product" + counter++;
                String category = categories[random.nextInt(categories.length)];
                int quantity = random.nextInt(100) + 1;
                double price = Math.round(random.nextDouble() * 10000) / 100.0;

                String line = product + "," + category + "," + quantity + "," + price;
                writer.write(line);
                writer.newLine();

                bytesWritten += line.getBytes().length + System.lineSeparator().getBytes().length;
            }
        }

        System.out.println("CSV generation completed!");
    }
}
