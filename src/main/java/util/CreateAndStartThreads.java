package util;

public class CreateAndStartThreads {
    public static void createAndStartThreads() {
        for (int i = 0; i < 3; i++) {
            int threadNumber = i;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println("Thread " + threadNumber + " - Iteration " + j);
                }
            });
            thread.start();
        }
    }
}
