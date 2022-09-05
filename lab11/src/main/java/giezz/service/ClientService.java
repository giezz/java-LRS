package giezz.service;

import giezz.entity.Client;
import giezz.entity.OrderInfo;
import giezz.entity.Product;

import java.util.List;
import java.util.Set;

import static giezz.util.HibernateUtil.session;
import static giezz.util.HibernateUtil.sessionFactory;

public class ClientService {

//    public static void addProductToClient(long clientId, long productId) {
//        session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Client client = session.get(Client.class, clientId);
//        Product product = session.get(Product.class, productId);
//        client.getOrders().
//        session.getTransaction().commit();
//        session.close();
//    }

    public static void create(Client client) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(client);
        session.getTransaction().commit();
        session.close();
    }

    public static Client get(int id) {
        return sessionFactory.openSession().get(Client.class, id);
    }

    public static List<Client> getAll() {
        return sessionFactory.openSession().createQuery("from Client", Client.class).getResultList();
    }

    public static Set<OrderInfo> getAllOrders(Client client) {
        return client.getOrders();
    }

    public static void remove(Client client) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(client);
        session.getTransaction().commit();
        session.close();
    }
}
