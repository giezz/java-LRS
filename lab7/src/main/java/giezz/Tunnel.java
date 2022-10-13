package giezz;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final Semaphore semaphore;

    private final int maxCarInTunnel;

    private static int tunnelCount = 1;

    public Tunnel(int maxCarInTunnel) {
        this.length = 80;
        this.description = "Тоннель " + tunnelCount + " " + length + " метров";
        this.semaphore = new Semaphore(maxCarInTunnel);
        this.maxCarInTunnel = maxCarInTunnel;
        tunnelCount++;
    }

    @Override
    public void go(Car car) {
        try {
            semaphore.acquire();
            System.out.println(car.getName() + " готовится к этапу(ждет): " + description);
            System.out.println(car.getName() + " начал этап: " + description);
            Thread.sleep(length / car.getSpeed() * 1000L);
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(car.getName() + " закончил этап: " +
                    description);
        }
    }

    public int getMaxCarInTunnel() {
        return maxCarInTunnel;
    }
}
