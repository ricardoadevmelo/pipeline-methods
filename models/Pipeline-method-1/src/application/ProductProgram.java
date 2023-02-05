package application;

import entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductProgram {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = scanner.nextLine();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            List<Product> productList = new ArrayList<>();

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                productList.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = bufferedReader.readLine();
            }

            double average = productList.stream()
                    .map(product -> product.getPrice()) // .map(Product::getPrice)
                    .reduce(0.0, (x, y) -> x + y) / productList.size(); // .reduce(0.0, Double::sum) / productList.size();

            System.out.printf("Average price: %.2f\n", average);

            Comparator<String> comparator = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
            // Comparator<String> comparator = Comparator.comparing(String::toUpperCase);

            List<String> stringList = productList.stream()
                    .filter(product -> product.getPrice() < average)
                    .map(product -> product.getName()) //.map(Product::getName)
                    .sorted(comparator.reversed()) // toList())
                    .collect(Collectors.toList());

            stringList.forEach(System.out::println);

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        scanner.close();
    }
}