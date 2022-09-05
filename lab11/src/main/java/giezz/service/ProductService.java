package giezz.service;

import giezz.entity.Client;
import giezz.entity.Product;

import java.util.List;

import static giezz.util.HibernateUtil.session;
import static giezz.util.HibernateUtil.sessionFactory;

public class ProductService {

    //не добавляет клиента к продукту
    public static void addClientToProduct(long productId, long clientId) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, productId);
        Client client = session.get(Client.class, clientId);
        product.getClients().add(client);
        session.getTransaction().commit();
        session.close();
    }

    public static void create(Product product) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
        session.close();
    }

    public static Product get(int id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public static List<Product> getAll() {
        return sessionFactory.openSession().createQuery("from Product", Product.class).getResultList();
    }

    public static List<Client> getAllClients(Product product) {
        return product.getClients();
    }

    public static void remove(Product product) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(product);
        session.getTransaction().commit();
        session.close();
    }
}
