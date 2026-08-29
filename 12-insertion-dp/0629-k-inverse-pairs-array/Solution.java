/**
 * LeetCode 629. K Inverse Pairs Array
 * Approach: Top-down memoized recursion -- ways(n, k) is the count of
 * permutations of 1..n with exactly k inverse pairs, built by inserting
 * the value n into a permutation of 1..n-1: placing it so it creates i
 * new inversions (i = 0..n-1) sums ways(n-1, k-i). Rather than summing
 * that window directly, the classic telescoping identity
 * ways(n,k) = ways(n,k-1) + ways(n-1,k) - ways(n-1,k-n) turns each step
 * into O(1) work.
 * Time: O(n * k) | Space: O(n * k)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private Long[][] dp;

    public int kInversePairs(int n, int k) {
        dp = new Long[n + 1][k + 1];
        return (int) ways(n, k);
    }

    private long ways(int n, int k) {
        if (k < 0) return 0;
        if (n == 0) return k == 0 ? 1 : 0;
        if (k == 0) return 1;
        if (dp[n][k] != null) return dp[n][k];
        long result = (ways(n, k - 1) + ways(n - 1, k)) % MOD;
        if (k - n >= 0) {
            result = (result - ways(n - 1, k - n) + MOD) % MOD;
        }
        dp[n][k] = result;
        return result;
    }
}
