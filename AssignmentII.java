public class AssignmentII {

    // Result class to store the indices of the maximum subarray and its sum
    public static class Result {
        int left;
        int right;
        int middle;

        // Constructor for the Result class
        public Result(int left, int right, int middle) {
            this.left = left;
            this.right = right;
            this.middle = middle;
        }
    }

    // The main function that uses divide and conquer to find the maximum subarray
    public static Result findMaximumSubarray(int[] prices, int low, int high) {
        // Base case: if there's only one element in the subarray, return it
        if (low == high) {
            return new Result(low, high, prices[low]);
        } else {
            int mid = (low + high) / 2;

            // Find the maximum subarray in the left half
            Result leftResult = findMaximumSubarray(prices, low, mid);

            // Find the maximum subarray in the right half
            Result rightResult = findMaximumSubarray(prices, mid + 1, high);

            // Find the maximum subarray that crosses the midpoint
            Result crossResult = findMaxCrossingSubarray(prices, low, mid, high);

            // Determine which of the three results is the maximum
            if (leftResult.middle >= rightResult.middle && leftResult.middle >= crossResult.middle) {
                return leftResult;
            } else if (rightResult.middle >= leftResult.middle && rightResult.middle >= crossResult.middle) {
                return rightResult;
            } else {
                return crossResult;
            }
        }
    }

    // Function to find the maximum subarray that crosses the midpoint
    public static Result findMaxCrossingSubarray(int[] prices, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;

        // Finds the maximum sum in the left half
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

        // Finds the maximum sum in the right half
        for (int i = mid + 1; i <= high; i++) {
            sum += prices[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        //  this will return the result with the maximum sum and its indices
        return new Result(maxLeft, maxRight, leftSum + rightSum);
    }

    public static void main(String[] args) {
        int[] prices = {100, 113, 85, 105,102,86,81,101,94,101,94,101,79,94,90,97};
        int[] changes = {13, -3, -25, 20, -3,-16,-23,18, 20,-7,12,-5,-22,15,-5,7};

        Result result = findMaximumSubarray(changes, 0, changes.length - 1);
        System.out.println("Maximum Subarray: ");
        System.out.println("Left index: " + result.left);
        System.out.println("Right index: " + result.right);
        System.out.println("Middle index: " + result.middle);
    }
}