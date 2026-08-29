import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 964. Least Operators to Express Number
 * Approach: Top-down memoized recursion -- for target >= x, find the
 * smallest power x^(n+1) exceeding it (so x^n <= target < x^(n+1)).
 * Using x^n as a term costs n operators (n-1 multiplications to build it
 * plus 1 to combine it), leaving target - x^n to solve recursively;
 * overshooting to x^(n+1) instead costs n+1, leaving the overshoot
 * x^(n+1) - target to subtract back off recursively (only worth trying
 * when that overshoot is itself less than target). For target < x, the
 * base case directly compares building target as a sum of x/x ones
 * against building x once and subtracting a sum of ones down to target.
 * Time: O(log_x(target)^2) | Space: O(log_x(target))
 */
class Solution {
    private int x;
    private Map<Integer, Integer> dp;

    public int leastOpsExpressTarget(int x, int target) {
        this.x = x;
        this.dp = new HashMap<>();
        return solve(target);
    }

    private int solve(int target) {
        if (x > target) {
            return Math.min(2 * target - 1, 2 * (x - target));
        }
        if (x == target) return 0;
        Integer cached = dp.get(target);
        if (cached != null) return cached;

        long prod = x;
        int n = 0;
        while (prod <= target) {
            prod *= x;
            n++;
        }
        // prod == x^(n+1) here, and x^n == prod / x <= target < prod.
        int result = solve((int) (target - prod / x)) + n;
        if (prod < 2L * target) {
            result = Math.min(result, solve((int) (prod - target)) + n + 1);
        }
        dp.put(target, result);
        return result;
    }
}
