package org.example.producerConsumer.withSync.arrayBlockingQueue;

import org.example.producerConsumer.Buffer;
import org.example.producerConsumer.Consumer;
import org.example.producerConsumer.Producer;
import org.example.producerConsumer.withSync.arrayBlockingQueue.BlockingBuffer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingBufferTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Buffer sharedLocation = new BlockingBuffer();

        executorService.execute (new Producer(sharedLocation));
        executorService.execute(new Consumer(sharedLocation));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
