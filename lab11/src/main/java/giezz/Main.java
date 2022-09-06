package giezz;

import giezz.entity.Client;
import giezz.entity.OrderInfo;
import giezz.entity.Product;
import giezz.service.ClientService;
import giezz.service.OrderInfoService;
import giezz.service.ProductService;
import giezz.util.HibernateUtil;
import giezz.util.Util;

import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
//        ClientService.create(new Client("Misha"));
////        ProductService.create(new Product("Phone", 200));
        Client client = ClientService.get(1);
        Product product = ProductService.get(1);
        Util.buy(client, product);
        System.out.println(
                OrderInfoService.getAllOrdersByClient(client));
        HibernateUtil.sessionFactory.close();
    }
}