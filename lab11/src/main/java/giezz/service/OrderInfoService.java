package giezz.service;

import giezz.entity.OrderInfo;

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
}
