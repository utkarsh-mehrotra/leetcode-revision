/**
 * LeetCode 416. Partition Equal Subset Sum
 * Approach: If the total sum is odd, no split is possible. Otherwise this
 * reduces to a 0/1 subset-sum knapsack for target = sum/2, iterating amounts
 * in reverse so each number is used at most once.
 * Time: O(n * sum) | Space: O(sum)
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
        }
        return dp[target];
    }
}
