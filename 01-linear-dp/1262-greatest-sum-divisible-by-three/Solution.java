/**
 * LeetCode 1262. Greatest Sum Divisible by Three
 * Approach: DP over 3 states -- the best achievable sum for each remainder
 * mod 3 seen so far. Each number transitions every reachable remainder
 * state to a new one.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int num : nums) {
            int[] next = dp.clone();
            for (int r = 0; r < 3; r++) {
                if (dp[r] == Integer.MIN_VALUE) continue;
                int newRemainder = (r + num) % 3;
                next[newRemainder] = Math.max(next[newRemainder], dp[r] + num);
            }
            dp = next;
        }
        return dp[0];
    }
}
