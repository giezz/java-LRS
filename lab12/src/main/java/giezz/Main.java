package giezz;

import giezz.model.Item;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static giezz.HibernateUtil.sessionFactory;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        int threadAmount = 8;
        AtomicInteger updateCount = new AtomicInteger();
        AtomicInteger rollbackCount = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(8);
        Thread[] threads = new Thread[threadAmount];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                Session session = sessionFactory.openSession();
                for (int j = 0; j < 20000; j++) {
                    try {
                        session.beginTransaction();
                        Item item = session.get(Item.class, (long) (Math.random() * 40 + 1));
                        item.setVal(item.getVal() + 1);
                        session.persist(item);
                        session.getTransaction().commit();
                        System.out.println(Thread.currentThread().getName() + " commited");
                        updateCount.getAndIncrement();
                    } catch (HibernateException | OptimisticLockException e) {
                        session.getTransaction().rollback();
                        System.out.println(Thread.currentThread().getName() + " rollback");
                        System.err.println("in " + Thread.currentThread().getName() + " " + e.getMessage());
                        rollbackCount.getAndIncrement();
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                session.close();
            }, "Thread " + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        sessionFactory.close();
        System.out.println("update count" + updateCount);
        System.out.println("rollback count" + rollbackCount);
    }


}