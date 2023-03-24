package org.example.producerConsumer;

public interface Buffer {
    void blockingPut(int value) throws InterruptedException;
    int blockingGet() throws InterruptedException;
}
