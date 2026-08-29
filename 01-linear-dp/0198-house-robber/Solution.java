/**
 * LeetCode 198. House Robber
 * Approach: Rolling DP -- best[i] = max(best[i-1], best[i-2] + nums[i]).
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int rob(int[] nums) {
        int prev2 = 0, prev1 = 0;
        for (int num : nums) {
            int curr = Math.max(prev1, prev2 + num);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
