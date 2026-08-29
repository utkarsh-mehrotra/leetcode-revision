import java.util.Arrays;

/**
 * LeetCode 611. Valid Triangle Number
 * Approach: Sort, then fix the LARGEST side of each triple (from the
 * end) and converge two pointers over everything smaller than it. Since
 * the array is sorted, nums[left] + nums[right] > nums[k] being true
 * means EVERY pair (x, right) with left <= x < right also satisfies it
 * (their sum is only larger) -- so right-left such pairs are counted at
 * once and right decrements; otherwise left advances to find a larger
 * sum.
 * Time: O(n²) | Space: O(n) sort, O(1) extra
 */
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        for (int k = n - 1; k >= 2; k--) {
            int left = 0, right = k - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[k]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }
}
