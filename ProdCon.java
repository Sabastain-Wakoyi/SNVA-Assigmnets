import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProdCon {

    private static final int BUFFER_SIZE = 7;
    private static final int NUM_PRODUCERS = 3;
    private static final int NUM_CONSUMERS = 2;
    private static final int SLEEP_TIME = 10000; // 10 seconds

    private static int[] buffer = new int[BUFFER_SIZE];
    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore full = new Semaphore(0);
    private static Semaphore empty = new Semaphore(BUFFER_SIZE);

    private static int count = 0;
    private static int in = 0;
    private static int out = 0;

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        Thread[] producers = new Thread[NUM_PRODUCERS];
        Thread[] consumers = new Thread[NUM_CONSUMERS];

        for (int i = 0; i < NUM_PRODUCERS; i++) {
            producers[i] = new Thread(new Producer());
            producers[i].start();
        }

        for (int i = 0; i < NUM_CONSUMERS; i++) {
            consumers[i] = new Thread(new Consumer());
            consumers[i].start();
        }

        Thread.sleep(SLEEP_TIME);

        for (Thread producer : producers) {
            producer.interrupt();
        }

        for (Thread consumer : consumers) {
            consumer.interrupt();
        }
    }

    static class Producer implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(random.nextInt(5000) + 1000); // Sleep randomly between 1 and 5 seconds
                    int item = random.nextInt();
                    empty.acquire();
                    mutex.acquire();
                    if (count < BUFFER_SIZE) {
                        buffer[in] = item;
                        in = (in + 1) % BUFFER_SIZE;
                        count++;
                        System.out.println("Producer produced " + item);
                    }
                    mutex.release();
                    full.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(random.nextInt(5000) + 1000); // Sleep randomly between 1 and 5 seconds
                    full.acquire();
                    mutex.acquire();
                    if (count > 0) {
                        int item = buffer[out];
                        out = (out + 1) % BUFFER_SIZE;
                        count--;
                        System.out.println("Consumer consumed " + item);
                    }
                    mutex.release();
                    empty.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
