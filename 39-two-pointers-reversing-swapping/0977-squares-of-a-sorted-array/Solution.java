/**
 * LeetCode 977. Squares of a Sorted Array
 * Approach: Two pointers converging from both ends of the (already
 * sorted) input. The largest square always comes from whichever end has
 * the greater absolute value, so filling the result array back-to-front
 * by repeatedly taking that larger square keeps everything correctly
 * ordered in one linear pass.
 * Time: O(n) | Space: O(n) output
 */
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];
            if (leftSq > rightSq) {
                result[i] = leftSq;
                left++;
            } else {
                result[i] = rightSq;
                right--;
            }
        }
        return result;
    }
}
