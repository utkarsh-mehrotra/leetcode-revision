/**
 * LeetCode 905. Sort Array By Parity
 * Approach: Two pointers converging from both ends. `left` scans forward
 * for an odd value; when found, it's swapped with `right` (which only
 * moves once its own position is known to hold an odd value), pushing
 * evens to the front and odds to the back in a single in-place pass.
 * Time: O(n) | Space: O(1) extra
 */
class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 == 0) {
                left++;
            } else if (nums[right] % 2 != 0) {
                right--;
            } else {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        return nums;
    }
}
