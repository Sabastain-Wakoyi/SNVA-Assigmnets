//import java.util.Random;
//
//public class Assign2T {
//
//    static int[] prices;
//
//    public static void main(String[] args) {
//        // generate random prices for 100 days
//        prices = new int[100];
//        Random rand = new Random();
//        for (int i = 0; i < prices.length; i++) {
//            prices[i] = 50 + rand.nextInt(91);
//        }
//
//        // calculate daily changes in prices
//        int[] changes = new int[prices.length - 1];
//        for (int i = 0; i < changes.length; i++) {
//            changes[i] = prices[i + 1] - prices[i];
//        }
//
//        //  maximum subarray of daily changes
//        Result result = findMaximumSubarray(changes, 0, changes.length - 1);
//
//        // this print the results
//        System.out.println("Left index: " + result.left);
//        System.out.println("Right index: " + result.right);
//        System.out.println("Middle index: " + (result.left + result.right) / 2);
//        System.out.println("Sum: " + result.sum);
//    }
//
//    private static Result findMaximumSubarray(int[] array, int low, int high) {
//        if (low == high) {
//            return new Result(low, high, array[low]);
//        }
//        int mid = (low + high) / 2;
//        Result leftResult = findMaximumSubarray(array, low, mid);
//        Result rightResult = findMaximumSubarray(array, mid + 1, high);
//        Result crossResult = findMaxCrossingSubarray(array, low, mid, high);
//        if (leftResult.sum >= rightResult.sum && leftResult.sum >= crossResult.sum) {
//            return leftResult;
//        } else if (rightResult.sum >= leftResult.sum && rightResult.sum >= crossResult.sum) {
//            return rightResult;
//        } else {
//            return crossResult;
//        }
//    }
//
//    private static Result findMaxCrossingSubarray(int[] array, int low, int mid, int high) {
//        int leftSum = Integer.MIN_VALUE;
//        int sum = 0;
//        int maxLeft = 0;
//        for (int i = mid; i >= low; i--) {
//            sum += array[i];
//            if (sum > leftSum) {
//                leftSum = sum;
//                maxLeft = i;
//            }
//        }
//        int rightSum = Integer.MIN_VALUE;
//        sum = 0;
//        int maxRight = 0;
//        for (int i = mid + 1; i <= high; i++) {
//            sum += array[i];
//            if (sum > rightSum) {
//                rightSum = sum;
//                maxRight = i;
//            }
//        }
//        return new Result(maxLeft, maxRight, leftSum + rightSum);
//    }
//
//    static class Result {
//        int left;
//        int right;
//        int sum;
//
//        Result(int left, int right, int sum) {
//            this.left = left;
//            this.right = right;
//            this.sum = sum;
//        }
//    }
//}
