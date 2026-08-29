/**
 * LeetCode 1420. Build Array Where You Can Find The Maximum Exactly K Comparisons
 * Approach: Top-down memoized recursion over (index, maxSoFar, searchCost)
 * -- placing any value <= maxSoFar keeps the cost unchanged (maxSoFar such
 * choices, all leading to the same next state), while placing a value v >
 * maxSoFar sets a new running max = v and costs one more "search cost"
 * (each such v is a genuinely distinct future state).
 * Time: O(n * m^2 * k) | Space: O(n * m * k)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int n, m, k;
    private Integer[][][] dp;

    public int numOfArrays(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.dp = new Integer[n + 1][m + 1][k + 1];
        return solve(0, 0, 0);
    }

    private int solve(int i, int maxSoFar, int cost) {
        if (cost > k) return 0;
        if (i == n) return cost == k ? 1 : 0;
        if (dp[i][maxSoFar][cost] != null) return dp[i][maxSoFar][cost];
        long total = (long) maxSoFar * solve(i + 1, maxSoFar, cost); // any value <= maxSoFar
        for (int v = maxSoFar + 1; v <= m; v++) {
            total = (total + solve(i + 1, v, cost + 1)) % MOD; // v becomes the new running max
        }
        total %= MOD;
        dp[i][maxSoFar][cost] = (int) total;
        return (int) total;
    }
}
