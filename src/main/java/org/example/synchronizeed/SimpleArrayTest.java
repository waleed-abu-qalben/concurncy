package org.example.synchronizeed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleArrayTest {
    public static void main(String[] args) {
        SimpleArray sharedSimpleArray = new SimpleArray(12);

        ArrayWriter writer1 = new ArrayWriter(sharedSimpleArray, 1);
        ArrayWriter writer2 = new ArrayWriter(sharedSimpleArray, 11);
        ArrayWriter writer3 = new ArrayWriter(sharedSimpleArray, 4);
        ArrayWriter writer4 = new ArrayWriter(sharedSimpleArray, 7);


        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(writer1);
        executorService.execute(writer2);
        executorService.execute(writer3);
        executorService.execute(writer4);

        executorService.shutdown();

        try {
            boolean taskEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);

            if(taskEnded) {
                System.out.println("\n content of simple Array");
                System.out.println(sharedSimpleArray);
            } else {
                System.out.println("Timed out while await task to finish");
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}