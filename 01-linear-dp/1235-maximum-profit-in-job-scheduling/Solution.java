import java.util.Arrays;

/**
 * LeetCode 1235. Maximum Profit in Job Scheduling
 * Approach: Top-down memoized recursion -- after sorting jobs by start
 * time, best(i) is the max profit from jobs[i:], either skipping job i or
 * taking it and jumping to the first later job whose start doesn't
 * overlap job i's end (found via binary search).
 * Time: O(n log n) | Space: O(n)
 */
class Solution {
    private int[] start, end, profit;
    private Integer[] memo;
    private int n;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        n = startTime.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(startTime[a], startTime[b]));

        this.start = new int[n];
        this.end = new int[n];
        this.profit = new int[n];
        for (int i = 0; i < n; i++) {
            this.start[i] = startTime[order[i]];
            this.end[i] = endTime[order[i]];
            this.profit[i] = profit[order[i]];
        }
        this.memo = new Integer[n];
        return best(0);
    }

    private int best(int i) {
        if (i == n) return 0;
        if (memo[i] != null) return memo[i];
        int skip = best(i + 1);
        int take = profit[i] + best(nextIndex(i));
        int result = Math.max(skip, take);
        memo[i] = result;
        return result;
    }

    // First index j > i with start[j] >= end[i].
    private int nextIndex(int i) {
        int lo = i + 1, hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (start[mid] >= end[i]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
