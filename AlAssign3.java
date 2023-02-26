import java.util.Random;

public class AlAssign3 {
    private int[] heap;
    private int size;

    public AlAssign3() {
        heap = new int[100];
        size = 0;
    }

    public void insert(int value) {
        if (size == heap.length) {
            throw new IllegalStateException("Heap is full");
        }

        heap[size] = value;
        trickleUp(size);
        size++;
    }

    public int removeMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap[0];
        heap[0] = heap[size-1];
        size--;
        trickleDown(0);
        return max;
    }

    public void increaseKey(int index, int value) {
        if (value < heap[index]) {
            throw new IllegalArgumentException("New value is less than current value");
        }

        heap[index] = value;
        trickleUp(index);
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    private void trickleUp(int index) {
        int parent = (index-1)/2;
        int tmp = heap[index];
        while (index > 0 && heap[parent] < tmp) {
            heap[index] = heap[parent];
            index = parent;
            parent = (index-1)/2;
        }
        heap[index] = tmp;
    }

    private void trickleDown(int index) {
        int largerChild;
        int tmp = heap[index];
        while (index < size/2) {
            int leftChild = 2*index+1;
            int rightChild = leftChild+1;
            if (rightChild < size && heap[leftChild] < heap[rightChild]) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
            if (tmp >= heap[largerChild]) {
                break;
            }
            heap[index] = heap[largerChild];
            index = largerChild;
        }
        heap[index] = tmp;
    }

    public static void main(String[] args) {
        AlAssign3 pq = new AlAssign3();
        Random rand = new Random();

        // generate random numbers and insert into heap
        for (int i = 0; i < 100; i++) {
            int value = rand.nextInt(501);
            pq.insert(value);
        }

        System.out.print("Heap: ");
        pq.printHeap();

        // extract max number and display heap
        int max = pq.removeMax();
        System.out.println("Max: " + max);
        System.out.print("Heap: ");
        pq.printHeap();

        // increase key of 50th element and display heap
        int index = 49; // 50th element has index 49 (zero-based indexing)
        int value = pq.heap[index] + 50;
        pq.increaseKey(index, value);
        System.out.print("Heap: ");
        pq.printHeap();
    }
}








