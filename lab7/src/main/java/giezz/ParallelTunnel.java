package giezz;

public class ParallelTunnel extends Stage {

    private final Tunnel tunnel1;
    private final Tunnel tunnel2;
    private static int carsInTunnel1Count;

    public ParallelTunnel(Tunnel tunnel1, Tunnel tunnel2) {
        this.tunnel1 = tunnel1;
        this.tunnel2 = tunnel2;
    }

    @Override
    public void go(Car car) {
        int maxCarsInTunnel1 = tunnel1.getMaxCarInTunnel();

        if (carsInTunnel1Count < maxCarsInTunnel1) {
            carsInTunnel1Count++;
            tunnel1.go(car);
        } else {
            tunnel2.go(car);
        }
    }
}
