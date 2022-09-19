package giezz;

import giezz.config.SpringConfig;
import giezz.shop.Cart;
import giezz.shop.OrderService;
import giezz.shop.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(String[] args) {
        ProductService.printAll();

        Cart cart = context.getBean(Cart.class);
        cart.addProduct(ProductService.findByTitle("Name 3"));
        cart.addProduct(ProductService.findByTitle("Name 1"));
        cart.addProduct(ProductService.findByTitle("Name 3"));
        cart.addProduct(ProductService.findByTitle("Name 1"));

        System.out.println();

        OrderService orderService = context.getBean(OrderService.class);
        orderService.createOrder();
        orderService.createOrder(ProductService.findByTitle("Name 1"), ProductService.findByTitle("Name 2"));
    }
}
