/**
 * LeetCode 795. Number of Subarrays with Bounded Maximum
 * Approach: Caterpillar/sliding-window counting via inclusion-exclusion:
 * countAtMost(bound) = number of subarrays whose every element is
 * <= bound, computed with a single sliding window whose length resets to
 * zero at any element exceeding bound (each window length added to the
 * running total counts every subarray ending at the current position
 * that stays within the window). The answer for "max in [left, right]"
 * is then countAtMost(right) - countAtMost(left - 1).
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return countAtMost(nums, right) - countAtMost(nums, left - 1);
    }

    private int countAtMost(int[] nums, int bound) {
        int count = 0, windowLen = 0;
        for (int num : nums) {
            windowLen = num <= bound ? windowLen + 1 : 0;
            count += windowLen;
        }
        return count;
    }
}
