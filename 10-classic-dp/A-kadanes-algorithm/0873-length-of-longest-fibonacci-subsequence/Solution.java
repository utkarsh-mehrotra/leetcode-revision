import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 873. Length of Longest Fibonacci-Like Subsequence
 * Approach: Top-down memoized recursion over (j, i) -- lengthEndingAt(j,i)
 * is the length of the longest Fibonacci-like run ending with the pair
 * (arr[j], arr[i]) (j < i). Its predecessor value would be arr[i]-arr[j];
 * if that value exists at some index k < j, the run extends
 * lengthEndingAt(k,j); otherwise this pair is the start of a fresh run.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] arr;
    private Map<Integer, Integer> indexOf;
    private Integer[][] dp;

    public int lenLongestFibSubseq(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        indexOf = new HashMap<>();
        for (int i = 0; i < n; i++) indexOf.put(arr[i], i);
        dp = new Integer[n][n];

        int best = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                best = Math.max(best, lengthEndingAt(j, i));
            }
        }
        return best >= 3 ? best : 0;
    }

    private int lengthEndingAt(int j, int i) {
        if (dp[j][i] != null) return dp[j][i];
        int predecessorValue = arr[i] - arr[j];
        int result = 2;
        if (predecessorValue < arr[j]) {
            Integer k = indexOf.get(predecessorValue);
            if (k != null) {
                result = lengthEndingAt(k, j) + 1;
            }
        }
        dp[j][i] = result;
        return result;
    }
}
