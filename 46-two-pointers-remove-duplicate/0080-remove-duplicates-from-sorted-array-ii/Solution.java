/**
 * LeetCode 80. Remove Duplicates from Sorted Array II
 * Approach: Slow/fast pointers, generalizing 26 to allow up to 2 copies
 * of each value. A value at `fast` is kept whenever fewer than 2 elements
 * have been written yet, or it differs from the value written 2 slots
 * back at `slow` (guaranteeing no more than 2 copies ever survive).
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (slow < 2 || nums[fast] != nums[slow - 2]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
