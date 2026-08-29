/**
 * LeetCode 935. Knight Dialer
 * Approach: Top-down memoized recursion over (hopsRemaining, digit) --
 * ways(h, d) sums ways(h-1, next) over every knight move from d.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int[][] MOVES = {
        {4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9},
        {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}
    };
    private Long[][] memo;

    public int knightDialer(int n) {
        memo = new Long[n][10];
        long total = 0;
        for (int digit = 0; digit < 10; digit++) {
            total = (total + ways(n - 1, digit)) % MOD;
        }
        return (int) total;
    }

    private long ways(int hopsRemaining, int digit) {
        if (hopsRemaining == 0) return 1;
        if (memo[hopsRemaining][digit] != null) return memo[hopsRemaining][digit];
        long total = 0;
        for (int next : MOVES[digit]) {
            total = (total + ways(hopsRemaining - 1, next)) % MOD;
        }
        memo[hopsRemaining][digit] = total;
        return total;
    }
}
