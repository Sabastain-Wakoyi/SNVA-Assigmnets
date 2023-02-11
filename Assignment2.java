//import java.util.Random;
//
//public class Assignment2 {
//
//    static int[] prices;
//
//    public static void main(String[] args) {
//        int[] day = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
//        int[] price = {100, 113, 85, 105,102,86,81,101,94,101,94,101,79,94,90,97};
//        int[] priceChange = {13, -3, -25, 20, -3,-16,-23,18,20,-7,12,-5,-22,15,-5,7};
//
//        // random prices for 100 days are generated
//        prices = new int[100];
//        Random rand = new Random();
//        for (int i = 0; i < prices.length; i++) {
//            prices[i] = 50 + rand.nextInt(120);
//        }
//
//        // this will calculate the daily changes in prices
//        int[] changes = new int[prices.length - 1];
//        for (int i = 0; i < changes.length; i++) {
//            changes[i] = prices[i + 1] - prices[i];
//        }
//
//        // this finds the maximum subarray of daily changes
//        Result result = findMaximumSubarray(changes, 0, changes.length - 1);
//
//        // print the result for left index, right index and the middle index
//        System.out.println("Left index: " + result.left);
//        System.out.println("Right index: " + result.right);
//        System.out.println("Middle index: " + (result.left + result.right) / 2);
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


public class Assignment2 {

    public static class Result {
        int low;
        int high;
        int sum;

        public Result(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }
    }

    public static Result findMaximumSubarray(int[] prices, int low, int high) {
        if (low == high) {
            return new Result(low, high, prices[low]);
        } else {
            int mid = (low + high) / 2;
            Result leftResult = findMaximumSubarray(prices, low, mid);
            Result rightResult = findMaximumSubarray(prices, mid + 1, high);
            Result crossResult = findMaxCrossingSubarray(prices, low, mid, high);

            if (leftResult.sum >= rightResult.sum && leftResult.sum >= crossResult.sum) {
                return leftResult;
            } else if (rightResult.sum >= leftResult.sum && rightResult.sum >= crossResult.sum) {
                return rightResult;
            } else {
                return crossResult;
            }
        }
    }

    public static Result findMaxCrossingSubarray(int[] prices, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;
        for (int i = mid; i >= low; i--) {
            sum += prices[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += prices[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new Result(maxLeft, maxRight, leftSum + rightSum);
    }

    public static void main(String[] args) {
        int[] prices = {100, 113, 85, 105,102,86,81,101,94,101,94,101,79,94,90,97};
        int[] changes = {13, -3, -25, 20, -3,-16,-23,18, 20,-7,12,-5,-22,15,-5,7};

        Result result = findMaximumSubarray(changes, 0, changes.length - 1);
        System.out.println("Maximum Subarray: ");
        System.out.println("Low index: " + result.low);
        System.out.println("High index: " + result.high);
        System.out.println("Sum: " + result.sum);
    }
}
