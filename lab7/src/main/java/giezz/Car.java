package giezz;

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
    private CyclicBarrier cyclicBarrier;
    private CountDownLatch countDownLatch;
    private static int carPositionOnFinish = 0;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch) {
        this.race = race;
        this.speed = speed;
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            cyclicBarrier.await();
            System.out.println(this.name + " готов");
            cyclicBarrier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            try {
                if (race.getStages().indexOf(race.getStages().get(i)) != race.getStages().size() - 1) {
                    race.getStages().get(i).go(this);
                    countDownLatch.countDown();
                    countDownLatch.await();
                } else {
                    race.getStages().get(i).go(this);
                    carPositionOnFinish++;
                    switch (carPositionOnFinish) {
                        case 1:
                            System.out.println(name + "WINNER");
                            break;
                        case 2:
                            System.out.println(name + "SECOND PLACE");
                            break;
                        case 3:
                            System.out.println(name + "THIRD PLACE");
                            break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}