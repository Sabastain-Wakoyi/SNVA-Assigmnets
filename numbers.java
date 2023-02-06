import java.util.Random;

;

public class numbers {
        public static void main(String[] args) {
            int n = 6; // size of the array
            int[] arr = new int[n];
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                arr[i] = random.nextInt();
            }
            long start = System.currentTimeMillis();
            int[] numbers  = {};
            long end = System.currentTimeMillis();
            System.out.println("Time taken for algorithm with " + n + " elements: " + (end - start) + " milliseconds");
        }
    }



