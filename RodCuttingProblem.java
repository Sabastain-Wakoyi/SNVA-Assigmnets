import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class RodCuttingTest {
    static int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    static int[] memo = new int[11];
    static int count1, count2, count3;

    // Implementation 1: Recursive top-down implementation
    public static int cutRod1(int n) {
        count1++;
        if (n == 0) {
            return 0;
        }
        int revenue = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            revenue = Math.max(revenue, prices[i] + cutRod1(n - i));
        }
        return revenue;
    }

    // Implementation 2: Recursive top-down implementation with memoization
    public static int cutRod2(int n) {
        count2++;
        if (memo[n] != 0) {
            return memo[n];
        }
        if (n == 0) {
            return 0;
        }
        int revenue = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            revenue = Math.max(revenue, prices[i] + cutRod2(n - i));
        }
        memo[n] = revenue;
        return revenue;
    }

    // Implementation 3: Recursive bottom-up implementation with memoization
    public static int cutRod3(int n) {
        memo[0] = 0;
        for (int j = 1; j <= n; j++) {
            int revenue = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                revenue = Math.max(revenue, prices[i] + memo[j - i]);
            }
            memo[j] = revenue;
            count3++;
        }
        return memo[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the length of the rod: ");
        int n = sc.nextInt();

        // Implementation 1: Recursive top-down implementation
        count1 = 0;
        int revenue1 = cutRod1(n);
        System.out.println("Implementation 1: Optimal revenue for length " + n + " is " + revenue1);
        System.out.println("Total recursive calls: " + count1);

        // Implementation 2: Recursive top-down implementation with memoization
        count2 = 0;
        Arrays.fill(memo, 0);
        int revenue2 = cutRod2(n);
        System.out.println("Implementation 2: Optimal revenue for length " + n + " is " + revenue2);
        System.out.println("Total recursive calls: " + count2);

        // Implementation 3: Recursive bottom-up implementation with memoization
        count3 = 0;
        Arrays.fill(memo, 0);
        int revenue3 = cutRod3(n);
        System.out.println("Implementation 3: Optimal revenue for length " + n + " is " + revenue3);
        System.out.println("Total iterations: " + count3);

        sc.close();
    }
}
