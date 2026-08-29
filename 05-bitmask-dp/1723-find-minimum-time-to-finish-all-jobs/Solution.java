/**
 * LeetCode 1723. Find Minimum Time to Finish All Jobs
 * Approach: Top-down memoized recursion over (jobMask, workersLeft) --
 * with 1 worker left, they must take every remaining job (the mask's
 * total, precomputed for every mask in O(1) via a lowest-set-bit
 * recurrence); otherwise try every non-empty submask as "this worker's
 * jobs" and minimize the worse of that submask's load vs. the best
 * achievable for the rest with one fewer worker.
 * Time: O(3^n) | Space: O(2^n * k)
 */
class Solution {
    private int[] maskSum;
    private Integer[][] dp;

    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int full = 1 << n;
        maskSum = new int[full];
        for (int mask = 1; mask < full; mask++) {
            int lowestBit = mask & (-mask);
            int idx = Integer.numberOfTrailingZeros(lowestBit);
            maskSum[mask] = maskSum[mask ^ lowestBit] + jobs[idx];
        }
        dp = new Integer[full][k + 1];
        return best(full - 1, k);
    }

    private int best(int mask, int workersLeft) {
        if (workersLeft == 1) return maskSum[mask];
        if (mask == 0) return 0;
        if (dp[mask][workersLeft] != null) return dp[mask][workersLeft];
        int result = Integer.MAX_VALUE;
        for (int sub = mask; sub > 0; sub = (sub - 1) & mask) {
            int candidate = Math.max(maskSum[sub], best(mask ^ sub, workersLeft - 1));
            result = Math.min(result, candidate);
        }
        dp[mask][workersLeft] = result;
        return result;
    }
}
