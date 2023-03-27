
import java.util.*;

public class KnapsackDP {

    public static void main(String[] args) {

        int[] weights = {1, 3, 4, 5}; // weight of each item
        int[] values = {1, 4, 5, 7}; // value of each item
        int maxCapacity = 7; // maximum capacity of the knapsack

        int n = weights.length;
        int[][] dp = new int[n+1][maxCapacity+1];

        // fill the table using the recurrence relation
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxCapacity; j++) {
                if (weights[i-1] <= j) {
                    dp[i][j] = Math.max(values[i-1] + dp[i-1][j-weights[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        // print the maximum value that can be obtained
        System.out.println("Maximum value that can be obtained: " + dp[n][maxCapacity]);
    }
}
