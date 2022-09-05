package giezz;

import giezz.entity.Client;
import giezz.entity.Product;
import giezz.service.ClientService;
import giezz.service.ProductService;
import giezz.util.HibernateUtil;

import static giezz.util.Util.buy;
import static giezz.util.Util.showProductsByPerson;

public class Main {
    public static void main(String[] args) {
        buy(3 ,3);
        HibernateUtil.sessionFactory.close();
    }
}