/**
 * LeetCode 903. Valid Permutations for DI Sequence
 * Approach: Top-down memoized recursion over (position, rank) -- ways(i,j)
 * counts permutations where the value placed at position i is the j-th
 * smallest among the values still available at that point. An 'I' at
 * s[i-1] requires the previous rank to have been smaller than j, so we sum
 * ways(i-1, k) over k < j; a 'D' requires the opposite, summing k >= j.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private String s;
    private Long[][] dp;

    public int numPermsDISequence(String s) {
        this.s = s;
        int n = s.length();
        this.dp = new Long[n + 1][n + 1];
        long total = 0;
        for (int j = 0; j <= n; j++) {
            total = (total + ways(n, j)) % MOD;
        }
        return (int) total;
    }

    private long ways(int i, int j) {
        if (i == 0) return j == 0 ? 1 : 0;
        if (dp[i][j] != null) return dp[i][j];
        long total = 0;
        char move = s.charAt(i - 1);
        if (move == 'I') {
            for (int k = 0; k < j; k++) total = (total + ways(i - 1, k)) % MOD;
        } else {
            for (int k = j; k < i; k++) total = (total + ways(i - 1, k)) % MOD;
        }
        dp[i][j] = total;
        return total;
    }
}
