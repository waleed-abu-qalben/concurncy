package org.example.Callable_Future;


import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class TimeData {
    public Instant start;
    public Instant end;

    public double timeInSeconds() {
        return Duration.between(start , end).toMillis() / 1000.0;
    }

}
public class FibonacciDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // perform synchronous fibonacci(45) and fibonacci(44) calculations
         System.out.println("Synchronous Long Running Calculations");
        TimeData synchronousResult1 = startFibonacci(45);
        TimeData synchronousResult2 = startFibonacci(44);
        double synchronousTime =
                calculateTime(synchronousResult1, synchronousResult2);
        System.out.printf(
                " Total calculation time = %.3f seconds%n", synchronousTime);

        // perform asynchronous fibonacci(45) and fibonacci(44) calculations
        System.out.printf(
                "%nAsynchronous Long Running Calculations%n");
        CompletableFuture<TimeData> futureResult1=
                CompletableFuture.supplyAsync(() -> startFibonacci(45));

        CompletableFuture<TimeData> futureResult2=
                CompletableFuture.supplyAsync(() -> startFibonacci(44));

        TimeData asynchronousResult1 = futureResult1.get();
        TimeData asynchronousResult2 = futureResult2.get();

        double asynchronousTime =
                calculateTime(asynchronousResult1, asynchronousResult2);
        System.out.printf(
                 " Total calculation time = %.3f seconds%n", asynchronousTime);

        String percentage = NumberFormat.getPercentInstance().format(
                 synchronousTime / asynchronousTime);
         System.out.printf("%nSynchronous calculations took %s" +
                " more time than the asynchronous calculations%n", percentage);
    }

    // executes function fibonacci asynchronously
    private static TimeData startFibonacci(int n) {
      TimeData timeData = new TimeData();
        System.out.printf(" Calculating fibonacci(%d)%n", n);
        timeData.start = Instant.now();
        long fibonacciValue  = fibonacci(n);
        timeData.end = Instant.now();
        displayResults(n, fibonacciValue, timeData);
        return timeData;
    }

    private static long fibonacci(long n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }


    private static void displayResults(int n, long value, TimeData timeData) {
        System.out.printf(" fibonacci(%d) = %d%n", n, value);
         System.out.printf(
                 " Calculation time for fibonacci(%d) = %.3f seconds%n",
                 n, timeData.timeInSeconds());
    }

    private static double calculateTime(TimeData result1, TimeData result2) {
        TimeData bothThreads = new TimeData();

        bothThreads.start = result1.start.compareTo(result2.start) < 0 ?
                result1.start : result2.start;
        bothThreads.end  = result1.end.compareTo(result2.end) > 0 ?
                result1.end : result2.end;
        return bothThreads.timeInSeconds();
    }


}
