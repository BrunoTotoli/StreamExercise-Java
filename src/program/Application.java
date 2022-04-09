package program;

import entities.Product;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Application {
    public static void main(String[] args) {

        Path path = Paths.get("D:\\Project\\in.txt");
        List<Product> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line = br.readLine();
            while (line != null) {
                String[] filds = line.split(",");
                list.add(new Product(filds[0], Double.parseDouble(filds[1])));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Double averagePrice = list.stream().mapToDouble(Product::getPrice).sum() / list.size();
        System.out.println(String.format("%.2f", averagePrice));

        List<String> names = list.stream()
                .filter(product -> product.getPrice() < averagePrice)
                .sorted(Comparator.comparing(Product::getName).reversed())
                .map(Product::getName)
                .toList();

        names.forEach(System.out::println);


    }
}
