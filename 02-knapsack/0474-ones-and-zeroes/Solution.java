/**
 * LeetCode 474. Ones and Zeroes
 * Approach: 0/1 knapsack with two capacities (zeros, ones). Top-down
 * memoized recursion over (index, zerosLeft, onesLeft) -- skip the current
 * string or take it if its 0/1 counts fit within the remaining budget.
 * Time: O(strs.length * m * n) | Space: O(strs.length * m * n)
 */
class Solution {
    private int[][] counts; // counts[i] = {zeros, ones} for strs[i]
    private Integer[][][] dp;

    public int findMaxForm(String[] strs, int m, int n) {
        counts = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            for (char c : strs[i].toCharArray()) {
                counts[i][c - '0']++;
            }
        }
        dp = new Integer[strs.length + 1][m + 1][n + 1];
        return solve(0, m, n);
    }

    private int solve(int i, int zerosLeft, int onesLeft) {
        if (i == counts.length) return 0;
        if (dp[i][zerosLeft][onesLeft] != null) return dp[i][zerosLeft][onesLeft];
        int result = solve(i + 1, zerosLeft, onesLeft); // skip
        int zeros = counts[i][0], ones = counts[i][1];
        if (zeros <= zerosLeft && ones <= onesLeft) {
            result = Math.max(result, 1 + solve(i + 1, zerosLeft - zeros, onesLeft - ones));
        }
        dp[i][zerosLeft][onesLeft] = result;
        return result;
    }
}
