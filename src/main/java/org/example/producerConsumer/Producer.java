package org.example.producerConsumer;

import org.example.producerConsumer.Buffer;

import java.security.SecureRandom;

public class Producer implements Runnable{
    private static final SecureRandom generate = new SecureRandom();
    private final Buffer sharedLocation;

    public Producer(Buffer sharedLocation) {
        this.sharedLocation = sharedLocation;
    }



    @Override
    public void run() {
        int sum = 0;
        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep(generate.nextInt(3000));
                sharedLocation.blockingPut(count);
                sum += count;
                //System.out.printf("\t%2dn",sum);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Producer done producing");
    }
}
