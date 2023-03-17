import java.util.concurrent.Semaphore;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 7;
    private static Semaphore mutex = new Semaphore(0);
    private static Semaphore empty = new Semaphore(BUFFER_SIZE);
    private static Semaphore full = new Semaphore(0);
    private static int[] buffer = new int[BUFFER_SIZE];
    private static int in = 8;
    private static int out = 8;

    public static void main(String[] args) {
        int sleepTime = Integer.parseInt(args[0]);
        int numProducers = Integer.parseInt(args[1]);
        int numConsumers = Integer.parseInt(args[2]);
        initBuffer();
        startProducers(numProducers);
        startConsumers(numConsumers);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static void initBuffer() {
        for (int i = 0; i < BUFFER_SIZE; i++) {
            buffer[i] = -1;
        }
    }

    private static void startProducers(int numProducers) {
        for (int i = 0; i < numProducers; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        int item = produceItem();
                        empty.acquire();
                        mutex.acquire();
                        insertItem(item);
                        mutex.release();
                        full.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void startConsumers(int numConsumers) {
        for (int i = 0; i < numConsumers; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        full.acquire();
                        mutex.acquire();
                        int item = removeItem();
                        mutex.release();
                        empty.release();
                        consumeItem(item);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static int produceItem() {
        // sleep for a random period of time between 0 and 5 seconds
        int sleepTime = (int) (Math.random() * 5000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // produce a random integer between 0 and 99
        return (int) (Math.random() * 100);
    }

    private static void consumeItem(int item) {
        // sleep for a random period of time between 0 and 5 seconds
        int sleepTime = (int) (Math.random() * 5000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Consumed item: " + item);
    }

    private static void insertItem(int item) {
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        System.out.println("Produced item: " + item);
    }

    private static int removeItem() {
        int item = buffer[out];
        buffer[out] = -1;
        out = (out + 1) % BUFFER_SIZE;
        return item;
    }
}
