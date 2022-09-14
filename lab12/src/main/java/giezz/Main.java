package giezz;

import giezz.model.Item;

import java.util.Random;

import static giezz.HibernateUtil.session;
import static giezz.HibernateUtil.sessionFactory;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        int threadAmount = 8;
        Random random = new Random();
        Thread[] threads = new Thread[threadAmount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                for (int j = 0; j < 20000; j++) {
                    Item item = session.get(Item.class, random.nextInt(40) + 1);
                    item.setVal(item.getVal() + 1);
                    session.flush();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                session.getTransaction().commit();
                session.close();
            }, "Thread " + i);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        sessionFactory.close();
    }
}