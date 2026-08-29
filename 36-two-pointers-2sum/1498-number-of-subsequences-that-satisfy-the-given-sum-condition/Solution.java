import java.util.Arrays;

/**
 * LeetCode 1498. Number of Subsequences That Satisfy the Given Sum Condition
 * Approach: Sort, then converge two pointers from both ends. Whenever
 * nums[left] + nums[right] <= target, EVERY subsequence that uses
 * nums[left] as its minimum and picks any subset of the (right - left)
 * elements strictly between left and right (each of which is also <=
 * nums[right] - nums[left], so still satisfies the bound) is valid --
 * that's 2^(right-left) subsequences in one shot, so left advances.
 * Otherwise nums[right] can never be a valid max with the current left,
 * so right retreats. Powers of two are precomputed mod 1e9+7.
 * Time: O(n log n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) pow2[i] = (int) ((pow2[i - 1] * 2L) % MOD);

        int left = 0, right = n - 1;
        long count = 0;
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + pow2[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }
        return (int) count;
    }
}
