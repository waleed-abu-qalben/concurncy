package org.example.synchronizeed;

import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray {
    private static final SecureRandom generator  = new SecureRandom();
    private final int[] arr;
    private int writeIndex = 0;

    public SimpleArray(int size) {
        arr = new int[size];

    }

     synchronized public void add(int value) {
        int position = writeIndex;

        try {
            Thread.sleep(generator.nextInt(600));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        arr[position] = value;
        writeIndex++;
        System.out.printf("%s wrote %2d to element %d.%n",
                Thread.currentThread().getName(), value, position);
         System.out.printf("Next write index: %d%n", writeIndex);


    }

    public String toString() {
        return Arrays.toString(arr);
    }
}
class ArrayWriter implements  Runnable{
    private final SimpleArray sharedSimpleArray;
    private final int startValue;

    ArrayWriter(SimpleArray sharedSimpleArray, int startValue) {
        this.sharedSimpleArray = sharedSimpleArray;
        this.startValue = startValue;
    }

    @Override
    public void run() {
        for (int i = startValue; i < startValue + 3; i++) {
            sharedSimpleArray.add(i);
        }
    }
}

