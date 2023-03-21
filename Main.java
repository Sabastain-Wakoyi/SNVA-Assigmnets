import java.util.concurrent.Semaphore;

public class Main {

    private static final int BUFFER_SIZE = 7;
    private static final int PRODUCER_COUNT = 2;
    private static final int CONSUMER_COUNT = 2;

    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore empty = new Semaphore(BUFFER_SIZE);
    private static Semaphore full = new Semaphore(0);

    private static int[] buffer = new int[BUFFER_SIZE];
    private static int in = 0;
    private static int out = 0;

    public static void main(String[] args) {
        // Parse command line arguments if needed
        // ...

        // Initialize the buffer and semaphores
        for (int i = 0; i < BUFFER_SIZE; i++) {
            buffer[i] = -1; // -1 indicates empty slot
        }

        // Create producer threads
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            Thread producerThread = new Thread(new Producer(i));
            producerThread.start();
        }

        // Create consumer threads
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            Thread consumerThread = new Thread(new Consumer(i));
            consumerThread.start();
        }

        // Sleep for a period of time before terminating
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Terminate the application
        System.exit(0);
    }

    static class Producer implements Runnable {
        private int id;

        public Producer(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                // Generate random item to insert into buffer
                int item = (int) (Math.random() * 100);

                try {
                    // Wait for an empty slot in the buffer
                    empty.acquire();

                    // Acquire mutex lock to protect critical section
                    mutex.acquire();

                    // Insert item into buffer
                    buffer[in] = item;
                    in = (in + 1) % BUFFER_SIZE;
                    System.out.println("Producer " + id + " produced item " + item);

                    // Release mutex lock and signal full semaphore
                    mutex.release();
                    full.release();

                    // Sleep for a random period of time before producing again
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private int id;

        public Consumer(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                try {
                    // Wait for a full slot in the buffer
                    full.acquire();

                    // Acquire mutex lock to protect critical section
                    mutex.acquire();

                    // Remove item from buffer
                    int item = buffer[out];
                    buffer[out] = -1;
                    out = (out + 1) % BUFFER_SIZE;
                    System.out.println("Consumer " + id + " consumed item " + item);

                    // Release mutex lock and signal empty semaphore
                    mutex.release();
                    empty.release();

                    // Sleep for a random period of time before consuming again
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
