/**
 * LeetCode 983. Minimum Cost For Tickets
 * Approach: Top-down memoized recursion over the index into `days` --
 * dp(i) is the min cost to cover days[i:], choosing a 1-, 7-, or 30-day
 * pass and jumping to the first day not covered by it (binary search).
 * Time: O(n log n) | Space: O(n)
 */
class Solution {
    private int[] days;
    private int[] costs;
    private Integer[] dp;

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        this.dp = new Integer[days.length];
        return solve(0);
    }

    private int solve(int i) {
        if (i == days.length) return 0;
        if (dp[i] != null) return dp[i];
        int oneDay = costs[0] + solve(nextIndex(i, 1));
        int sevenDay = costs[1] + solve(nextIndex(i, 7));
        int thirtyDay = costs[2] + solve(nextIndex(i, 30));
        int result = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
        dp[i] = result;
        return result;
    }

    // First index j >= i such that days[j] >= days[i] + span.
    private int nextIndex(int i, int span) {
        int lo = i, hi = days.length;
        int boundary = days[i] + span;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (days[mid] >= boundary) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
