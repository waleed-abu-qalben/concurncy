package org.example.producerConsumer.withSync.boundedBuffers;

import org.example.producerConsumer.Buffer;

public class CircularBuffer implements Buffer {
    private final int buffer [] = {-1,-1,-1};
    private int writeIndex = 0;
    private int readIndex = 0;
    private int occupiedCells = 0;
    @Override
    public synchronized void blockingPut(int value) throws InterruptedException {
        while (occupiedCells == buffer.length) {
            System.out.printf("Buffer is full. Producer waits.%n");
            wait();
        }
        buffer[writeIndex] = value;
        writeIndex = (writeIndex + 1) % buffer.length;
        displayState("Producer writes " + value);
        occupiedCells++;
        notifyAll();

    }

    @Override
    public synchronized int blockingGet() throws InterruptedException {
        while (occupiedCells == 0) {
            System.out.printf("Buffer is empty. Consumer waits.%n");
            wait();
        }
        int readValue = buffer[readIndex];
        readIndex = (readIndex + 1) % buffer.length;
        occupiedCells--;
        displayState("Consumer reads " + readValue);
        notifyAll();
        return readValue;
    }
    public synchronized void displayState(String operation)
 {
         // output operation and number of occupied buffer cells
         System.out.printf("%s%s%d)%n%s", operation,
         " (buffer cells occupied: ", occupiedCells, "buffer cells: ");

         for (int value : buffer)
             System.out.printf(" %2d ", value); // output values in buffer

         System.out.printf("%n ");

         for (int i = 0; i < buffer.length; i++)
             System.out.print("---- ");

         System.out.printf("%n ");

         for (int i = 0; i < buffer.length; i++) {

             if (i == writeIndex && i == readIndex)
               System.out.print(" WR"); // both write and read index
             else if (i == writeIndex)
             System.out.print(" W "); // just write index
             else if (i == readIndex)
             System.out.print(" R "); // just read index
             else
             System.out.print(" "); // neither index

         }

     System.out.print("\n\n");


 }

}
