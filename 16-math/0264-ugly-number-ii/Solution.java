/**
 * LeetCode 264. Ugly Number II
 * Approach: Top-down memoized recursion, 1-indexed with ugly(1) = 1 as the
 * base case -- ugly(i) is built as the smallest of 2*ugly(p2), 3*ugly(p3),
 * 5*ugly(p5) for three pointers that each start at 1 and only advance
 * (possibly all at once, on ties, to avoid duplicates) when their
 * multiple is used. Recursing to i-1 first ensures the pointers -- which
 * always reference strictly earlier indices -- are fully advanced before
 * ugly(i) reads them.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private Long[] dp;
    private int p2 = 1, p3 = 1, p5 = 1;

    public int nthUglyNumber(int n) {
        dp = new Long[n + 1];
        return (int) ugly(n);
    }

    private long ugly(int i) {
        if (i <= 1) return 1;
        if (dp[i] != null) return dp[i];
        ugly(i - 1); // ensures pointers are advanced correctly through i-1 first
        long candidate2 = 2 * ugly(p2), candidate3 = 3 * ugly(p3), candidate5 = 5 * ugly(p5);
        long result = Math.min(candidate2, Math.min(candidate3, candidate5));
        if (result == candidate2) p2++;
        if (result == candidate3) p3++;
        if (result == candidate5) p5++;
        dp[i] = result;
        return result;
    }
}
