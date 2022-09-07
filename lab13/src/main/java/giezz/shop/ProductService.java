package giezz.shop;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {

    private static List<Product> products = new ArrayList<>();

    private ProductService() {
    }

    @PostConstruct
    public void preInit() {
        for (int i = 1; i <= 10; i++) {
            products.add(new Product(i, "Name " + i, i * 16));
        }
    }

    public static void printAll() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static Product findByTitle(String title) {
        for (Product product : products) {
            if (product.getTitle().equals(title)) return product;
        }
        return null;
    }

}
