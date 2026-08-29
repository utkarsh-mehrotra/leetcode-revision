/**
 * LeetCode 698. Partition to K Equal Sum Subsets
 * Approach: Top-down memoized recursion over `mask` (which numbers have
 * been placed into some bucket so far). The current partial bucket's sum
 * is always (sum of elements in mask) mod target, since every completed
 * bucket contributes exactly `target` -- so it's a deterministic function
 * of mask and doesn't need to be part of the memo key.
 * Time: O(n * 2^n) | Space: O(2^n)
 */
class Solution {
    private int[] nums;
    private int n;
    private int target;
    private Boolean[] dp;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % k != 0) return false;
        this.target = total / k;
        this.nums = nums;
        this.n = nums.length;
        for (int num : nums) if (num > target) return false;
        this.dp = new Boolean[1 << n];
        return solve(0, 0);
    }

    private boolean solve(int mask, int currentBucketSum) {
        if (mask == (1 << n) - 1) return true;
        if (dp[mask] != null) return dp[mask];
        boolean result = false;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) continue;
            int newSum = currentBucketSum + nums[i];
            if (newSum > target) continue;
            int nextMask = mask | (1 << i);
            int nextBucketSum = (newSum == target) ? 0 : newSum;
            if (solve(nextMask, nextBucketSum)) {
                result = true;
                break;
            }
        }
        dp[mask] = result;
        return result;
    }
}
