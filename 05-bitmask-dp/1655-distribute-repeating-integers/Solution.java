import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1655. Distribute Repeating Integers
 * Approach: Group nums by distinct value to get a list of counts. Top-down
 * memoized recursion over (valueIndex, unsatisfiedMask) -- for the current
 * distinct value, try every submask of the still-unsatisfied queries whose
 * total demand fits within this value's count, satisfy exactly that
 * submask with it, and recurse on the rest with the remaining values.
 * Time: O(distinctValues * 3^numQueries) | Space: O(distinctValues * 2^numQueries)
 */
class Solution {
    private int[] counts;
    private int[] quantity;
    private int m;
    private Boolean[][] dp;

    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.merge(num, 1, Integer::sum);
        counts = new int[freq.size()];
        int idx = 0;
        for (int c : freq.values()) counts[idx++] = c;

        this.quantity = quantity;
        this.m = quantity.length;
        dp = new Boolean[counts.length + 1][1 << m];
        return solve(0, (1 << m) - 1);
    }

    private boolean solve(int valueIndex, int unsatisfiedMask) {
        if (unsatisfiedMask == 0) return true;
        if (valueIndex == counts.length) return false;
        if (dp[valueIndex][unsatisfiedMask] != null) return dp[valueIndex][unsatisfiedMask];

        boolean result = false;
        int cap = counts[valueIndex];
        for (int sub = unsatisfiedMask; sub > 0; sub = (sub - 1) & unsatisfiedMask) {
            int demand = 0;
            for (int q = 0; q < m; q++) {
                if ((sub & (1 << q)) != 0) demand += quantity[q];
            }
            if (demand <= cap && solve(valueIndex + 1, unsatisfiedMask & ~sub)) {
                result = true;
                break;
            }
        }
        if (!result) {
            result = solve(valueIndex + 1, unsatisfiedMask); // this value satisfies no query
        }
        dp[valueIndex][unsatisfiedMask] = result;
        return result;
    }
}
