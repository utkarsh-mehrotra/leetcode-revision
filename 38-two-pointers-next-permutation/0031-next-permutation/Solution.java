/**
 * LeetCode 31. Next Permutation
 * Approach: Scan from the right to find the first index i where
 * nums[i] < nums[i+1] (the pivot -- everything after it is a
 * non-increasing "tail"). Swap it with the smallest value in that tail
 * still greater than it (the rightmost such value, found scanning from
 * the right), then reverse the tail with a two-pointer swap so it becomes
 * ascending -- the smallest possible arrangement of those trailing
 * digits, giving the overall next lexicographic permutation. If no pivot
 * exists, the array is the last permutation; reversing the whole thing
 * wraps around to the first.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
