package giezz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static final int CARS_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(CARS_COUNT);
        CountDownLatch countDownLatchForFinish = new CountDownLatch(CARS_COUNT);
        Car.setCountDownLatch(countDownLatch);
        Car.setCountDownLatchForFinish(countDownLatchForFinish);
        Car.setCyclicBarrier(cyclicBarrier);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60, "1"), new ParallelTunnel(new Tunnel(2), new Tunnel(Integer.MAX_VALUE)));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) {
            new Thread(car).start();
        }

        countDownLatch.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        countDownLatchForFinish.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        Car.checkWinners();
    }
}