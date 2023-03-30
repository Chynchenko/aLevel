package util;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrency {

    private static final int NUMBER_OF_THREADS = 4;
    private static final int NUMBER_OF_PHASES = 3;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(NUMBER_OF_THREADS);

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            executor.submit(new Task(startLatch, endLatch, i + 1));
        }

        startLatch.countDown();
        endLatch.await();

        executor.shutdown();
        System.out.println("All tasks completed");
    }

    public static class Task implements Runnable {

        private final CountDownLatch startLatch;
        private final CountDownLatch endLatch;
        private final int id;

        public Task(CountDownLatch startLatch, CountDownLatch endLatch, int id) {
            this.startLatch = startLatch;
            this.endLatch = endLatch;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                startLatch.await();
                for (int i = 1; i <= NUMBER_OF_PHASES; i++) {
                    int sleepTime = (int)(Math.random() * 10 + 1);
                    Thread.sleep(sleepTime * 1000);
                    System.out.println("Thread " + id + ", time " + sleepTime + ", phase " + i + " completed");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                endLatch.countDown();
            }
        }
    }
}