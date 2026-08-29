/**
 * LeetCode 1449. Form Largest Integer With Digits That Add up to Target
 * Approach: Top-down memoized recursion -- maxDigits(t) is the maximum
 * digit count whose costs sum exactly to t (unbounded knapsack maximizing
 * count). The answer's length is maxDigits(target); reconstruct the
 * lexicographically largest number by greedily choosing, at each step, the
 * largest digit whose remainder is still achievable in exactly one fewer digit.
 * Time: O(target * 9) | Space: O(target)
 */
class Solution {
    private int[] cost;
    private Integer[] dp;
    private static final int NEG_INF = Integer.MIN_VALUE / 2;

    public String largestNumber(int[] cost, int target) {
        this.cost = cost;
        this.dp = new Integer[target + 1];
        int length = maxDigits(target);
        if (length <= 0) return "0";

        StringBuilder sb = new StringBuilder();
        int remaining = target;
        while (remaining > 0) {
            // Pick the largest digit that keeps the rest of the number at max achievable length.
            for (int digit = 9; digit >= 1; digit--) {
                int c = cost[digit - 1];
                if (c <= remaining && maxDigits(remaining - c) == maxDigits(remaining) - 1) {
                    sb.append((char) ('0' + digit));
                    remaining -= c;
                    break;
                }
            }
        }
        return sb.toString();
    }

    private int maxDigits(int t) {
        if (t == 0) return 0;
        if (t < 0) return NEG_INF;
        if (dp[t] != null) return dp[t];
        int best = NEG_INF;
        for (int digit = 1; digit <= 9; digit++) {
            int c = cost[digit - 1];
            if (c <= t) {
                best = Math.max(best, maxDigits(t - c) + 1);
            }
        }
        dp[t] = best;
        return best;
    }
}
