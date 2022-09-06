package giezz.service;

import giezz.entity.Client;
import giezz.entity.OrderInfo;
import jakarta.persistence.Query;

import java.util.List;

import static giezz.util.HibernateUtil.session;
import static giezz.util.HibernateUtil.sessionFactory;


public class OrderInfoService {
    public static void create(OrderInfo orderInfo) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(orderInfo);
        session.getTransaction().commit();
        session.close();
    }

    public static List<OrderInfo> getAllOrdersByClient(Client client) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from OrderInfo where client = :client", OrderInfo.class);
        query.setParameter("client", client);
        List<OrderInfo> orderInfos= query.getResultList();
        session.getTransaction().commit();
        session.close();
        return orderInfos;
    }
}
