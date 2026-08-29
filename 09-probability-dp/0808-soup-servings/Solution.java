import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 808. Soup Servings
 * Approach: Work in units of 25ml so each serving operation subtracts a
 * small integer. Top-down memoized recursion over (a, b) -- the remaining
 * soup A and B -- averaging the 4 equally-likely serving operations, with
 * P(A empties first) counted fully and P(both empty together) counted as
 * half. For large n the probability provably converges to 1, so n is
 * capped up front to keep the recursion's state space bounded.
 * Time: O(1) amortized (bounded state space after capping) | Space: O(1) amortized
 */
class Solution {
    private Map<Long, Double> dp;

    public double soupServings(int n) {
        if (n >= 4800) return 1.0; // converges to 1 well before this; avoids unbounded recursion depth
        int servings = (n + 24) / 25; // round up to whole 25ml servings
        dp = new HashMap<>();
        return solve(servings, servings);
    }

    private double solve(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;
        long key = (long) a * 10000 + b;
        Double cached = dp.get(key);
        if (cached != null) return cached;
        double result = 0.25 * (
            solve(a - 100, b) +
            solve(a - 75, b - 25) +
            solve(a - 50, b - 50) +
            solve(a - 25, b - 75)
        );
        dp.put(key, result);
        return result;
    }
}
