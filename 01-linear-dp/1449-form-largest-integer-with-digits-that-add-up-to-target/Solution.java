import java.util.Arrays;

/**
 * LeetCode 1449. Form Largest Integer With Digits That Add up to Target
 * Approach: dp[t] = maximum number of digits whose costs sum exactly to t
 * (unbounded knapsack maximizing count). The answer's digit count is
 * dp[target]; reconstruct the lexicographically largest number by greedily
 * choosing the largest digit at each step that still leaves a remainder
 * achievable in exactly one fewer digit.
 * Time: O(target * 9) | Space: O(target)
 */
class Solution {
    public String largestNumber(int[] cost, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int t = 1; t <= target; t++) {
            for (int digit = 1; digit <= 9; digit++) {
                int c = cost[digit - 1];
                if (c <= t && dp[t - c] != Integer.MIN_VALUE) {
                    dp[t] = Math.max(dp[t], dp[t - c] + 1);
                }
            }
        }
        if (dp[target] <= 0) return "0";

        StringBuilder sb = new StringBuilder();
        int remaining = target;
        while (remaining > 0) {
            for (int digit = 9; digit >= 1; digit--) {
                int c = cost[digit - 1];
                if (c <= remaining && dp[remaining - c] == dp[remaining] - 1) {
                    sb.append((char) ('0' + digit));
                    remaining -= c;
                    break;
                }
            }
        }
        return sb.toString();
    }
}
