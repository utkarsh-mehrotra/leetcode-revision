/**
 * LeetCode 879. Profitable Schemes
 * Approach: Top-down memoized recursion over (index, membersLeft,
 * profitSoFar) -- skip or commit to each crime, capping profitSoFar at
 * minProfit since any profit >= minProfit counts the same going forward.
 * Time: O(crimes * n * minProfit) | Space: O(crimes * n * minProfit)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int[] group, profit;
    private int minProfit;
    private Integer[][][] dp;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        this.group = group;
        this.profit = profit;
        this.minProfit = minProfit;
        this.dp = new Integer[group.length + 1][n + 1][minProfit + 1];
        return solve(0, n, 0);
    }

    private int solve(int i, int membersLeft, int profitSoFar) {
        if (i == group.length) return profitSoFar >= minProfit ? 1 : 0;
        if (dp[i][membersLeft][profitSoFar] != null) return dp[i][membersLeft][profitSoFar];
        long result = solve(i + 1, membersLeft, profitSoFar); // skip this crime
        if (group[i] <= membersLeft) {
            int cappedProfit = Math.min(minProfit, profitSoFar + profit[i]);
            result = (result + solve(i + 1, membersLeft - group[i], cappedProfit)) % MOD;
        }
        dp[i][membersLeft][profitSoFar] = (int) result;
        return (int) result;
    }
}
