package org.example.producerConsumer.withSync.lock_condition;

import org.example.producerConsumer.Buffer;
import org.example.producerConsumer.Consumer;
import org.example.producerConsumer.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedBufferTest2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Buffer sharedLocation = new SynchronizedBuffer();
        System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation",
                 "Buffer", "Occupied", "---------", "------\t\t--------");
        executorService.execute(new Producer(sharedLocation));
         executorService.execute(new Consumer(sharedLocation));

         executorService.shutdown();
         executorService.awaitTermination(1, TimeUnit.MINUTES);

    }
}
