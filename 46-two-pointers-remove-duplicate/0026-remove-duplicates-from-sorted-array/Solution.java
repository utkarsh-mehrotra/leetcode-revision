/**
 * LeetCode 26. Remove Duplicates from Sorted Array
 * Approach: Slow/fast pointers. `slow` marks the end of the deduplicated
 * prefix; `fast` scans forward, and whenever it finds a value different
 * from the last kept one, it's written at `slow` and `slow` advances.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 1]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
