/**
 * LeetCode 189. Rotate Array
 * Approach: The classic three-reversal trick, each reversal itself a
 * two-pointer in-place swap: reverse the whole array, then reverse the
 * first k elements, then reverse the remaining n-k elements. Reversing
 * everything once puts the last k elements first but backward; reversing
 * each of the two resulting segments individually fixes their internal
 * order, yielding an in-place right rotation by k with no extra array.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }
}
