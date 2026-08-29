/**
 * LeetCode 1043. Partition Array for Maximum Sum
 * Approach: Top-down memoized recursion -- best(i) is the best achievable
 * sum for arr[i:], trying every partition-starting window of length 1..k.
 * Time: O(n * k) | Space: O(n)
 */
class Solution {
    private int[] arr;
    private int k;
    private Integer[] memo;

    public int maxSumAfterPartitioning(int[] arr, int k) {
        this.arr = arr;
        this.k = k;
        this.memo = new Integer[arr.length + 1];
        return best(0);
    }

    private int best(int i) {
        int n = arr.length;
        if (i == n) return 0;
        if (memo[i] != null) return memo[i];
        int result = 0;
        int windowMax = 0;
        for (int len = 1; len <= k && i + len <= n; len++) {
            windowMax = Math.max(windowMax, arr[i + len - 1]);
            result = Math.max(result, windowMax * len + best(i + len));
        }
        memo[i] = result;
        return result;
    }
}
