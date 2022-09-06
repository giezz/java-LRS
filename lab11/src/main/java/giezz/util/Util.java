package giezz.util;

import giezz.entity.Client;
import giezz.entity.OrderInfo;
import giezz.entity.Product;
import giezz.service.ClientService;
import giezz.service.OrderInfoService;
import giezz.service.ProductService;

import java.util.stream.Collectors;

public class Util {
    //
    public static void showProductsByPerson(int id) {
        System.out.println(
                ClientService.getAllOrders(
                                ClientService.get(id)
                        )
                        .stream()
                        .map(OrderInfo::getProduct)
                        .map(Product::getName)
                        .collect(Collectors.toList())
        );
    }

    public static void showProductsByPerson(Client client) {
        System.out.println(
                ClientService.getAllOrders(client)
                        .stream()
                        .map(OrderInfo::getProduct)
                        .map(Product::getName)
                        .collect(Collectors.toList())
        );
    }


    public static void findPersonsByProduct(int id) {
        System.out.println(
                ProductService.getAllClients(
                                ProductService.get(id)
                        )
                        .stream()
                        .map(OrderInfo::getProduct)
                        .map(Product::getName)
                        .collect(Collectors.toList())
        );
    }

    public static void findPersonsByProduct(Product product) {
        System.out.println(
                ProductService.getAllClients(product)
                        .stream()
                        .map(OrderInfo::getProduct)
                        .map(Product::getName)
                        .collect(Collectors.toList())
        );
    }

    public static void buy(Client client, Product product) {
        OrderInfoService.create(new OrderInfo(client, product));
    }
}
