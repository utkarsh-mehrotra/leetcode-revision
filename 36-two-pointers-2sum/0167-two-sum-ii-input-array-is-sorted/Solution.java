/**
 * LeetCode 167. Two Sum II - Input Array Is Sorted
 * Approach: Two pointers converging from both ends. Since the array is
 * sorted, moving left up increases the sum and moving right down
 * decreases it -- exactly one of those moves can ever be correct at each
 * step, so no combination is ever skipped.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) return new int[]{left + 1, right + 1};
            if (sum < target) left++;
            else right--;
        }
        return new int[]{-1, -1};
    }
}
