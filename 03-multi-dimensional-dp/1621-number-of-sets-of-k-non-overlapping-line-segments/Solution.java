/**
 * LeetCode 1621. Number of Sets of K Non-Overlapping Line Segments
 * Approach: Choosing k touching-allowed, non-overlapping segments from n
 * points is equivalent to choosing a non-decreasing sequence of 2k
 * boundary values from {0,...,n-1} (with repetition) -- a standard
 * multichoose count, C(n + 2k - 1, 2k). Computed via top-down memoized
 * recursion on Pascal's triangle identity C(a,b) = C(a-1,b-1) + C(a-1,b).
 * Time: O(n * k) | Space: O(n * k)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private Long[][] dp;

    public int numberOfSets(int n, int k) {
        int a = n + 2 * k - 1;
        int b = 2 * k;
        dp = new Long[a + 1][b + 1];
        return (int) choose(a, b);
    }

    private long choose(int a, int b) {
        if (b == 0 || b == a) return 1;
        if (b < 0 || b > a) return 0;
        if (dp[a][b] != null) return dp[a][b];
        long result = (choose(a - 1, b - 1) + choose(a - 1, b)) % MOD;
        dp[a][b] = result;
        return result;
    }
}
