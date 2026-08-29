/**
 * LeetCode 983. Minimum Cost For Tickets
 * Approach: Backward DP over calendar days. dp[d] = min cost to cover days
 * d..lastDay. Non-travel days simply inherit dp[d+1]; travel days pick the
 * cheapest of a 1-, 7-, or 30-day pass starting at d.
 * Time: O(lastDay) | Space: O(lastDay)
 */
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        boolean[] isTravelDay = new boolean[lastDay + 1];
        for (int day : days) isTravelDay[day] = true;

        int[] dp = new int[lastDay + 2];
        for (int d = lastDay; d >= 1; d--) {
            if (!isTravelDay[d]) {
                dp[d] = dp[d + 1];
                continue;
            }
            int oneDay = costs[0] + dp[Math.min(d + 1, lastDay + 1)];
            int sevenDay = costs[1] + dp[Math.min(d + 7, lastDay + 1)];
            int thirtyDay = costs[2] + dp[Math.min(d + 30, lastDay + 1)];
            dp[d] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
        }
        return dp[1];
    }
}
