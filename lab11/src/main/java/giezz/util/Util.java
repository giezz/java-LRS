package giezz.util;

import giezz.entity.Client;
import giezz.entity.Product;
import giezz.service.ClientService;
import giezz.service.ProductService;

import java.util.stream.Collectors;

public class Util {

    public static void showProductsByPerson(int id) {
        System.out.println(
                ClientService.getAllProducts(
                                ClientService.get(id)
                        )
                        .stream()
                        .map(Product::getName)
                        .collect(Collectors.toList())
        );
    }

    public static void showProductsByPerson(Client client) {
        System.out.println(
                ClientService.getAllProducts(client)
                        .stream()
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
                        .map(Client::getName)
                        .collect(Collectors.toList())
        );
    }

    public static void findPersonsByProduct(Product product) {
        System.out.println(
                ProductService.getAllClients(product)
                        .stream()
                        .map(Client::getName)
                        .collect(Collectors.toList())
        );
    }

    public static void buy(int clientId, int productId) {
        ClientService.addProductToClient(clientId, productId);
    }

    public static void buy(Client client, Product product) {
        ClientService.addProductToClient(client.getId(), product.getId());
    }
}
