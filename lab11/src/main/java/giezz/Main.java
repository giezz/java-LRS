package giezz;

import giezz.entity.Client;
import giezz.entity.OrderInfo;
import giezz.entity.Product;
import giezz.service.ClientService;
import giezz.service.OrderInfoService;
import giezz.service.ProductService;
import giezz.util.HibernateUtil;

import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
//        ClientService.addProductToClient(3, 3);
//        buy(3 ,3);
//        ClientService.create(new Client("Hrusha"));
        Client client = ClientService.get(1);
        Product product = ProductService.get(1);
        System.out.println(
                client.getOrders().stream()
                        .map(OrderInfo::getProduct)
                        .map(Product::getName)
                        .collect(Collectors.toList())
        );
        OrderInfoService.create(new OrderInfo(client, product));
        HibernateUtil.sessionFactory.close();
    }
}