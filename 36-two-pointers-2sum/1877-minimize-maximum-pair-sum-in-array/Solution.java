import java.util.Arrays;

/**
 * LeetCode 1877. Minimize Maximum Pair Sum in Array
 * Approach: Sort, then pair the smallest remaining element with the
 * largest remaining element (two pointers converging from both ends).
 * Pairing extremes together is the classic exchange-argument optimum for
 * minimizing the maximum pair sum: any other pairing can be shown to
 * produce a pair sum at least as large via a swap argument.
 * Time: O(n log n) | Space: O(1) extra
 */
class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int maxPairSum = 0;
        while (left < right) {
            maxPairSum = Math.max(maxPairSum, nums[left] + nums[right]);
            left++;
            right--;
        }
        return maxPairSum;
    }
}
