package program;

import entities.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println(averagePrice);


    }
}
