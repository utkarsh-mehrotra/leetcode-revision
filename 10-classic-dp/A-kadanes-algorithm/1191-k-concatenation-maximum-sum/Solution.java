/**
 * LeetCode 1191. K-Concatenation Maximum Sum
 * Approach: A best subarray spans at most 2 concatenations' worth of
 * "wraparound" (crossing a boundary more than once is never better), so
 * Kadane's algorithm (top-down memoized recursion) on 1 and on 2 copies
 * of arr covers every structurally distinct case; the k >= 3 case then
 * adds (k-2) whole-array copies only when the whole array sums positive.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int[] arr;
    private Integer[] dp;

    public int kConcatenationMaxSum(int[] arr, int k) {
        long totalSum = 0;
        for (int num : arr) totalSum += num;

        long singleMax = kadaneNonNegative(arr);
        if (k == 1) return (int) (singleMax % MOD);

        int[] doubled = new int[arr.length * 2];
        System.arraycopy(arr, 0, doubled, 0, arr.length);
        System.arraycopy(arr, 0, doubled, arr.length, arr.length);
        this.arr = doubled;
        this.dp = new Integer[doubled.length];
        long doubleMax = kadaneNonNegative(doubled);

        if (totalSum <= 0) return (int) (doubleMax % MOD);
        long result = (doubleMax + (long) (k - 2) * totalSum) % MOD;
        return (int) result;
    }

    private long kadaneNonNegative(int[] array) {
        this.arr = array;
        this.dp = new Integer[array.length];
        long best = 0;
        for (int i = 0; i < array.length; i++) {
            best = Math.max(best, endingAt(i));
        }
        return best;
    }

    private int endingAt(int i) {
        if (i == 0) return arr[0];
        if (dp[i] != null) return dp[i];
        int result = arr[i] + Math.max(0, endingAt(i - 1));
        dp[i] = result;
        return result;
    }
}
