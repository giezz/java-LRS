package giezz.util;

import giezz.entity.Client;
import giezz.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Product.class)
            .addAnnotatedClass(Client.class)
            .buildSessionFactory();

    public static Session session;
}

