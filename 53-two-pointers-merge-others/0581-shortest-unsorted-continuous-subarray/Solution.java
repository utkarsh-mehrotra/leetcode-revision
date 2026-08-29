/**
 * LeetCode 581. Shortest Unsorted Continuous Subarray
 * Approach: Two independent sweeps from opposite ends. A left-to-right
 * pass tracks the running max and records the rightmost index that's
 * still smaller than something seen earlier (the right boundary of the
 * out-of-place region); a right-to-left pass tracks the running min and
 * records the leftmost index still larger than something seen later
 * (the left boundary). Together they bound the minimal subarray whose
 * sort would fix the whole array.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int right = -1, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] < max) right = i;
            max = Math.max(max, nums[i]);
        }

        int left = -1, min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > min) left = i;
            min = Math.min(min, nums[i]);
        }

        return right == -1 ? 0 : right - left + 1;
    }
}
