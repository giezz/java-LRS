package giezz.service;

import giezz.entity.Client;
import giezz.entity.Product;

import java.util.List;

import static giezz.util.HibernateUtil.session;
import static giezz.util.HibernateUtil.sessionFactory;

public class ClientService {

    public static void addProductToClient(long clientId, long productId) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Client client = session.get(Client.class, clientId);
        Product product = session.get(Product.class, productId);
        client.addProduct(product);
        session.getTransaction().commit();
        session.close();
    }

    public static void create(Client client) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(client);
        session.getTransaction().commit();
        session.close();
    }

    public static Client get(int id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Client client = session.get(Client.class, id);
        session.getTransaction().commit();
        session.close();
        return client;
    }

    public static List<Client> getAll() {
        return sessionFactory.openSession().createQuery("from Client", Client.class).getResultList();
    }

    public static List<Product> getAllProducts(Client client) {
        return client.getProducts();
    }

    public static void remove(Client client) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(client);
        session.getTransaction().commit();
        session.close();
    }
}
