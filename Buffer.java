import java.util.Random;
import java.util.concurrent.Semaphore;

public class Buffer {

    private static final int BUFFER_SIZE = 7;
    private static final Semaphore empty = new Semaphore(BUFFER_SIZE);
    private static final Semaphore full = new Semaphore(0);
    private static final Semaphore mutex = new Semaphore(1);
    private static final buffer_item[] buffer = new buffer_item[BUFFER_SIZE];

    private static int in = 0;
    private static int out = 0;

    private static class buffer_item {
        int value;
    }

    private static class Producer implements Runnable {
        private Random rand = new Random();

        public void run() {
            while (true) {
                try {
                    int item = rand.nextInt();
                    empty.acquire();
                    mutex.acquire();
                    buffer[in].value = item;
                    in = (in + 1) % BUFFER_SIZE;
                    System.out.println("Produced item " + item);
                    mutex.release();
                    full.release();
                    Thread.sleep(rand.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private Random rand = new Random();

        public void run() {
            while (true) {
                try {
                    full.acquire();
                    mutex.acquire();
                    int item = buffer[out].value;
                    out = (out + 1) % BUFFER_SIZE;
                    System.out.println("Consumed item " + item);
                    mutex.release();
                    empty.release();
                    Thread.sleep(rand.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int sleepTime = Integer.parseInt(args[0]);
        int numProducers = Integer.parseInt(args[1]);
        int numConsumers = Integer.parseInt(args[2]);

        Thread[] producers = new Thread[numProducers];
        Thread[] consumers = new Thread[numConsumers];

        for (int i = 0; i < numProducers; i++) {
            producers[i] = new Thread(new Producer());
            producers[i].start();
        }

        for (int i = 0; i < numConsumers; i++) {
            consumers[i] = new Thread(new Consumer());
            consumers[i].start();
        }

        try {
            Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
