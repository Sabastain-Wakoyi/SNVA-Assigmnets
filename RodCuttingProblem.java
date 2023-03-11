//This code is a Java implementation of the Rod Cutting problem using three different approaches.
// The Rod Cutting problem is a classic optimization problem in which a rod of length n is given,
// and the goal is to cut the rod into smaller pieces of integer lengths to maximize the total revenue obtained from selling the pieces. The revenue obtained for a piece of length i is given by prices[i].
//The first approach, Implementation 1, uses a recursive top-down approach to solve the problem.
// It starts with the original length n and recursively tries all possible cuts and their respective revenues. It then returns the maximum revenue obtained from all these possible cuts. The main disadvantage of this approach is that it recomputes the same subproblems multiple times,
// leading to exponential time complexity.
//The second approach, Implementation 2, is an optimized version of the first approach that uses memoization to store the results of subproblems. It first checks whether the subproblem has already been solved and stored in the memo array. If yes, it directly returns the result, else it computes the result using a recursive top-down approach, stores it in the memo array, and returns the result.
// This approach eliminates the redundant computations and reduces the time complexity to linear.
//The third approach, Implementation 3, uses a dynamic programming bottom-up approach with memoization to solve the problem. It iteratively solves subproblems from the smallest size to the largest size and stores the results in the memo array. It also keeps track of the sizes of the pieces chosen for optimal revenue.
// Finally, it returns the optimal revenue for the original length n and displays the sizes of the pieces chosen for the optimal revenue. This approach has
// linear time complexity and is the most efficient among the three approaches.



import java.util.Arrays;
import java.util.Scanner;

class Rod {
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
        int[] choices = new int[n + 1]; // to keep track of the sizes of the pieces chosen for optimal revenue
        int revenue = 0;
        for (int j = 1; j <= n; j++) {
            revenue = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                count3++;
                int temp = prices[i] + memo[j - i];
                if (temp > revenue) {
                    revenue = temp;
                    choices[j] = i; // record the size of the piece chosen for optimal revenue
                }

            }
            memo[j] = revenue;

        }

        // Display the best decompositions of n
        System.out.print("Best decompositions of " + n + ": ");
        while (n > 0) {
            System.out.print(choices[n] + " ");
            n -= choices[n];
        }
        System.out.println();

        return revenue;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the length of the rod: ");
        int n = sc.nextInt();

        // Implementation 1: Recursive top-down implementation
        count1 = 0;
        long startTime1 = System.nanoTime();
        int revenue1 = cutRod1(n);
        long endTime1 = System.nanoTime();
        long timeElapsed1 = endTime1 - startTime1;
        System.out.println("Implementation 1 Recursive top-down implementation: Optimal revenue for length " + n + " is $" + revenue1);
        System.out.println("Total recursive calls: " + (count1 - 1));
        System.out.println("Time elapsed: " + timeElapsed1 + " ns");

        // Implementation 2: Recursive top-down implementation with memoization
        count2 = 0;
        Arrays.fill(memo, 0);
        long startTime2 = System.nanoTime();
        int revenue2 = cutRod2(n);
        long endTime2 = System.nanoTime();
        long timeElapsed2 = endTime2 - startTime2;
        System.out.println("Implementation 2 Recursive top-down implementation with memoization: Optimal revenue for length " + n + " is $" + revenue2);
        System.out.println("Total recursive calls: " + (count2 - 1));
        System.out.println("Time elapsed: " + timeElapsed2 + " nanoseconds");

        // Implementation 3: Recursive bottom-up implementation with memoization
        count3 = 0;
        Arrays.fill(memo, 0);
        System.out.println("Implementation 3 Recursive bottom-up implementation with memoization:");
        long startTime3 = System.nanoTime();
        int revenue3 = cutRod3(n);
        long endTime3 = System.nanoTime();
        long timeElapsed3 = endTime3 - startTime3;
        System.out.println("Optimal revenue for length " + n + " is $" + revenue3);
        System.out.println("Total iterations: " + count3);
        System.out.println("Time taken: " + timeElapsed3 + " nanoseconds");
    }
}


