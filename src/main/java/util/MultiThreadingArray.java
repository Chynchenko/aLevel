package util;

import lombok.SneakyThrows;
import java.util.Arrays;
import java.util.Random;
public class MultiThreadingArray {
    private static final Object MONITOR = new Object();

    private static final int SIZE_OF_ARRAY = 100;
    public static final int[] ARRAY = new int[SIZE_OF_ARRAY];
    private static int index = 0;

    private static final int CONT_OF_THREADS_FILL = 10;
    private static final int CONT_OF_THREADS_SUM = 4;

    private static final int NUMBER_OF_ELEMENTS_IN_THREAD_SUM = (int)Math.ceil((double)(SIZE_OF_ARRAY) / (double)(CONT_OF_THREADS_SUM));

    private static int sum;
    private static int count;

    private static final Random random = new Random();
    private static final int BOUND = 1000;

    @SneakyThrows
    public static void fillArray() {
        for (int i = 0; i < CONT_OF_THREADS_FILL; i++) {
            Fill fill = new Fill();
            fill.start();
        }
        while (ARRAY[ARRAY.length-1] == 0) {
            Thread.yield();
        }
        System.out.println(Arrays.toString(ARRAY));
    }

    @SneakyThrows
    public static void sumArray() {
        for (int i = 0; i < CONT_OF_THREADS_SUM; i++) {
            SumThread sumThread = new SumThread(i * NUMBER_OF_ELEMENTS_IN_THREAD_SUM, (i + 1) * NUMBER_OF_ELEMENTS_IN_THREAD_SUM -1);
            sumThread.start();
        }
        while (count < CONT_OF_THREADS_SUM) {
            Thread.yield();
        }
        System.out.println("Total sum  -  " + sum);

    }

    public static void checkSum(){
        int sumChecked = 0;
        for (int i = 0; i < SIZE_OF_ARRAY; i++) {
            sumChecked += ARRAY[i];
        }
        System.out.println("Total checked sum  -  " + sumChecked);
    }

    private static boolean canInsert(int number){
        for(int x = 0; x < index; x++){
            if(ARRAY[x] == number) {
                return false;
            }
        }
        return true;
    }

    static class Fill extends Thread {

        @Override
        public void run() {
            while (index < SIZE_OF_ARRAY) {
                int number = 1 + random.nextInt(BOUND);
                synchronized (MONITOR) {
                    if (canInsert(number) && (index < SIZE_OF_ARRAY)) {
                        ARRAY[index] = number;
                        index++;
                    }
                }
            }
        }
    }

    static class SumThread extends Thread {
        private final int from;
        private final int to;
        private int sumOfPart;

        public SumThread(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            for (int i = this.from; i <= this.to; i++) {
                if (i < SIZE_OF_ARRAY)
                    sumOfPart += ARRAY[i];
            }
            System.out.println(Thread.currentThread().getName() + ": sum of part -  " + sumOfPart);
            synchronized (MONITOR) {
                sum += sumOfPart;
                count++;
            }
        }
    }
}