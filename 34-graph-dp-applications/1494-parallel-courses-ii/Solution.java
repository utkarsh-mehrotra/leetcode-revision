/**
 * LeetCode 1494. Parallel Courses II
 * Approach: Top-down bitmask DP. dp[mask] = fewest semesters needed to
 * finish exactly the completed-course set `mask`. From `mask`, a course
 * is "available" this semester if it isn't done yet and every prerequisite
 * bit is already set in `mask`; any subset of the available set with size
 * <= k can legally be taken together, advancing to dp[mask | subset].
 * Enumerating every submask of the available set (not just the largest)
 * is necessary for correctness: taking fewer than the max this semester
 * can occasionally open a better combination next semester. Recursion
 * bottoms out when mask is the full course set.
 * Time: O(3^n) submask enumeration | Space: O(2^n)
 */
class Solution {
    private int[] prereqMask;
    private int k;
    private int fullMask;
    private Integer[] dp;

    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        this.k = k;
        this.prereqMask = new int[n];
        for (int[] rel : relations) {
            prereqMask[rel[1] - 1] |= 1 << (rel[0] - 1);
        }
        this.fullMask = (1 << n) - 1;
        this.dp = new Integer[1 << n];
        return solve(0);
    }

    private int solve(int mask) {
        if (mask == fullMask) return 0;
        if (dp[mask] != null) return dp[mask];

        int available = 0;
        for (int course = 0; course < prereqMask.length; course++) {
            if ((mask & (1 << course)) == 0 && (mask & prereqMask[course]) == prereqMask[course]) {
                available |= 1 << course;
            }
        }

        int best = Integer.MAX_VALUE;
        for (int sub = available; sub > 0; sub = (sub - 1) & available) {
            if (Integer.bitCount(sub) <= k) {
                best = Math.min(best, 1 + solve(mask | sub));
            }
        }

        dp[mask] = best;
        return best;
    }
}
