/**
 * LeetCode 746. Min Cost Climbing Stairs
 * Approach: Rolling DP where dp[i] is the minimum cost to reach step i;
 * the answer is the cheaper of reaching the top from either of the last two steps.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int prev2 = 0, prev1 = 0;
        for (int i = 2; i <= n; i++) {
            int curr = Math.min(prev1 + cost[i - 1], prev2 + cost[i - 2]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
