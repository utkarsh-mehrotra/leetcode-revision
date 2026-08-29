import java.util.Arrays;

/**
 * LeetCode 1235. Maximum Profit in Job Scheduling
 * Approach: Sort jobs by end time. dp[i] = best profit using a subset of
 * the first i (sorted) jobs. For each job, binary search on end times for
 * the latest earlier job compatible with it (end <= this job's start), and
 * take max(skip job, take job + dp[that index]).
 * Time: O(n log n) | Space: O(n)
 */
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(endTime[a], endTime[b]));

        int[] sortedStart = new int[n];
        int[] sortedEnd = new int[n];
        int[] sortedProfit = new int[n];
        for (int i = 0; i < n; i++) {
            sortedStart[i] = startTime[order[i]];
            sortedEnd[i] = endTime[order[i]];
            sortedProfit[i] = profit[order[i]];
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int prevCompatible = findLastCompatible(sortedEnd, i - 1, sortedStart[i - 1]);
            dp[i] = Math.max(dp[i - 1], sortedProfit[i - 1] + dp[prevCompatible + 1]);
        }
        return dp[n];
    }

    // Rightmost index in sortedEnd[0..bound) with end <= target, or -1.
    private int findLastCompatible(int[] sortedEnd, int bound, int target) {
        int lo = 0, hi = bound - 1, result = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (sortedEnd[mid] <= target) {
                result = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return result;
    }
}
