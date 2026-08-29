/**
 * LeetCode 552. Student Attendance Record II
 * Approach: Top-down memoized recursion over (daysLeft, absences, lateStreak)
 * -- count(d, a, l) sums the ways to append 'P', 'A' (only if a == 0), or
 * 'L' (only if l < 2) to a record of length d.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private Long[][][] dp;

    public int checkRecord(int n) {
        dp = new Long[n + 1][2][3];
        return (int) count(n, 0, 0);
    }

    private long count(int daysLeft, int absences, int lateStreak) {
        if (daysLeft == 0) return 1;
        if (dp[daysLeft][absences][lateStreak] != null) {
            return dp[daysLeft][absences][lateStreak];
        }
        long total = count(daysLeft - 1, absences, 0); // Present
        if (absences == 0) {
            total = (total + count(daysLeft - 1, 1, 0)) % MOD; // Absent
        }
        if (lateStreak < 2) {
            total = (total + count(daysLeft - 1, absences, lateStreak + 1)) % MOD; // Late
        }
        dp[daysLeft][absences][lateStreak] = total;
        return total;
    }
}
