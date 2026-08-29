/**
 * LeetCode 70. Climbing Stairs
 * Approach: Top-down memoized recursion -- ways(n) = ways(n-1) + ways(n-2),
 * each subproblem computed once and cached.
 * Time: O(n) | Space: O(n) (memo + recursion stack)
 */
class Solution {
    public int climbStairs(int n) {
        Integer[] memo = new Integer[n + 1];
        return climb(n, memo);
    }

    private int climb(int n, Integer[] memo) {
        if (n <= 2) return n;
        if (memo[n] != null) return memo[n];
        memo[n] = climb(n - 1, memo) + climb(n - 2, memo);
        return memo[n];
    }
}
