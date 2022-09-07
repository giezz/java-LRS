package giezz.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private int finalCost = 0;

    private Cart cart;

    public void createOrder() {
        System.out.println("Your order contains :");
        for (int i = 0; i < cart.getProducts().size(); i++) {
            System.out.println("|\t" + cart.getProduct(i));
            finalCost += cart.getProduct(i).getCost();
        }

        System.out.println("Final cost = " + finalCost);
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
