package giezz;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final int SIZE = 60_000_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите число потоков");
        Scanner scanner = new Scanner(System.in);
        int threadsAmount = scanner.nextInt();
        float[] floats = calculation(threadsAmount);
//        System.out.println(Arrays.toString(floats));
        System.out.println(floats[SIZE - 1]);
    }

    public static float[] calculation(int threadsAmount) throws InterruptedException {
        float[][] array;
        if (SIZE % threadsAmount == 0) {
            array = new float[threadsAmount][SIZE / threadsAmount];
            for (float[] floats : array) {
                Arrays.fill(floats, 1);
            }
        } else {
            array = new float[threadsAmount][];
            for (int i = 0; i < array.length; i++) {
                if (i < array.length - 1) {
                    array[i] = new float[SIZE / threadsAmount];
                } else {
                    array[i] = new float[SIZE / threadsAmount + SIZE % threadsAmount];
                }
            }
            for (float[] floats : array) {
                Arrays.fill(floats, 1);
            }
        }
        long a = System.currentTimeMillis();
        Thread[] threads = new Thread[threadsAmount];
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < array[finalI].length; j++) {
                    float delta = 0.2f + (finalI * (SIZE / threadsAmount) + j) / 5;
                    array[finalI][j] =
                            (float) (array[finalI][j] * Math.sin(delta) * Math.cos(delta) * Math.cos(0.4f + (finalI * (SIZE / threadsAmount) + j) / 2));
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        float[] finalArray = new float[SIZE];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, finalArray, i * array[0].length, array[i].length);
        }
        System.out.println(System.currentTimeMillis() - a);

        return finalArray;
    }
}