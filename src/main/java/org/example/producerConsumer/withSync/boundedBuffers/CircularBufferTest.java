package org.example.producerConsumer.withSync.boundedBuffers;

import org.example.producerConsumer.Buffer;
import org.example.producerConsumer.Consumer;
import org.example.producerConsumer.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CircularBufferTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CircularBuffer sharedLocation = new CircularBuffer();
        sharedLocation.displayState("Initial State");
        executorService.execute(new Producer(sharedLocation));
        executorService.execute(new Consumer(sharedLocation));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
