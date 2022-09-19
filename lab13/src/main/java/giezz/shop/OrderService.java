package giezz.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class OrderService {

    private Cart cart;

    public void createOrder() {
        int finalCost = 0;
        System.out.println("Your order contains :");
//        for (int i = 0; i < cart.getProducts().size(); i++) {
//            System.out.println("|\t" + cart.getProduct(i));
//            finalCost += cart.getProduct(i).getCost();
//        }

        for (Product product : cart.getProducts()) {
            System.out.println("|\t" + product);
            finalCost += product.getCost();
        }

        System.out.println("Final cost = " + finalCost);
    }

    public void createOrder(Product... products) {
        System.out.println("Your order contains :");
        int finalCost = 0;
        for (Product product : products) {
            System.out.println("|\t" + product);
            finalCost += product.getCost();
        }

        System.out.println("Final cost = " + finalCost);
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
