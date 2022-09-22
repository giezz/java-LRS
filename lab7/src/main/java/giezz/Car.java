package giezz;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private static CountDownLatch countDownLatch;
    private static CountDownLatch countDownLatchForFinish;
    private static CyclicBarrier cyclicBarrier;
    private static int carPositionOnFinish = 0;

    private static HashMap<String, String> positionsOnFinish = new LinkedHashMap<>();


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            cyclicBarrier.await();
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            countDownLatch.countDown();
            cyclicBarrier.await();
//            cyclicBarrier.reset();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            if (race.getStages().indexOf(race.getStages().get(i)) != race.getStages().size() - 1) {
                race.getStages().get(i).go(this);
            } else {
                race.getStages().get(i).go(this);
                carPositionOnFinish++;
                switch (carPositionOnFinish) {
                    case 1:
//                        System.out.println(name + " WINNER");
                        positionsOnFinish.put(name, "WINNER");
                        break;
                    case 2:
//                        System.out.println(name + " SECOND PLACE");
                        positionsOnFinish.put(name, "SECOND PLACE");
                        break;
                    case 3:
//                        System.out.println(name + " THIRD PLACE");
                        positionsOnFinish.put(name, "THIRD PLACE");
                        break;
                }
            }
        }
        countDownLatchForFinish.countDown();
    }

    public static void setCountDownLatch(CountDownLatch countDownLatch) {
        Car.countDownLatch = countDownLatch;
    }

    public static void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        Car.cyclicBarrier = cyclicBarrier;
    }

    public static void setCountDownLatchForFinish(CountDownLatch countDownLatchForFinish) {
        Car.countDownLatchForFinish = countDownLatchForFinish;
    }

    public static void checkWinners() {
        for (Map.Entry<String, String> stringStringEntry : positionsOnFinish.entrySet()) {
            System.out.println(stringStringEntry);
        }
    }

}