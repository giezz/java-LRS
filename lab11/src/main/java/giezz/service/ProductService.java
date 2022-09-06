package giezz.service;

import giezz.entity.Client;
import giezz.entity.OrderInfo;
import giezz.entity.Product;

import java.util.List;
import java.util.Set;

import static giezz.util.HibernateUtil.session;
import static giezz.util.HibernateUtil.sessionFactory;

public class ProductService {

    //не добавляет клиента к продукту
//    public static void addClientToProduct(long productId, long clientId) {
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Product product = session.get(Product.class, productId);
//        Client client = session.get(Client.class, clientId);
//        product.getClients().add(client);
//        session.getTransaction().commit();
//        session.close();
//    }

    public static void create(Product product) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
        session.close();
    }

    public static Product get(int id) {
        return sessionFactory.openSession().get(Product.class, id);
    }

    public static List<Product> getAll() {
        return sessionFactory.openSession().createQuery("from Product", Product.class).getResultList();
    }

    public static Set<OrderInfo> getAllClients(Product product) {
        return product.getOrders();
    }

    public static void remove(Product product) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(product);
        session.getTransaction().commit();
        session.close();
    }
}
