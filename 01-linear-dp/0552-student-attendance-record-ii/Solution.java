/**
 * LeetCode 552. Student Attendance Record II
 * Approach: DP over state (absences so far in {0,1}, trailing-late streak
 * in {0,1,2}) for each of n days, appending 'P', 'A', or 'L' when legal.
 * Rolled forward one day at a time instead of memoizing per-length records.
 * Time: O(n) | Space: O(1) (6 states)
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int checkRecord(int n) {
        // dp[a][l]: count of valid records so far with `a` absences and
        // `l` trailing lates.
        long[][] dp = new long[2][3];
        dp[0][0] = 1;
        for (int day = 0; day < n; day++) {
            long[][] next = new long[2][3];
            for (int a = 0; a < 2; a++) {
                for (int l = 0; l < 3; l++) {
                    long ways = dp[a][l];
                    if (ways == 0) continue;
                    next[a][0] = (next[a][0] + ways) % MOD; // Present
                    if (a == 0) {
                        next[1][0] = (next[1][0] + ways) % MOD; // Absent
                    }
                    if (l < 2) {
                        next[a][l + 1] = (next[a][l + 1] + ways) % MOD; // Late
                    }
                }
            }
            dp = next;
        }
        long total = 0;
        for (int a = 0; a < 2; a++) {
            for (int l = 0; l < 3; l++) {
                total = (total + dp[a][l]) % MOD;
            }
        }
        return (int) total;
    }
}
