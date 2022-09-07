package giezz.shop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void printAllProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int i){
        return products.get(i);
    }
}
