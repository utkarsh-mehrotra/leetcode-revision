import java.util.Arrays;

/**
 * LeetCode 935. Knight Dialer
 * Approach: DP over 10 states (current digit), transitioning through the
 * fixed knight-move adjacency list for a phone keypad, for n-1 steps.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int[][] MOVES = {
        {4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9},
        {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}
    };

    public int knightDialer(int n) {
        long[] dp = new long[10];
        Arrays.fill(dp, 1);
        for (int step = 1; step < n; step++) {
            long[] next = new long[10];
            for (int digit = 0; digit < 10; digit++) {
                for (int dest : MOVES[digit]) {
                    next[dest] = (next[dest] + dp[digit]) % MOD;
                }
            }
            dp = next;
        }
        long total = 0;
        for (long ways : dp) total = (total + ways) % MOD;
        return (int) total;
    }
}
