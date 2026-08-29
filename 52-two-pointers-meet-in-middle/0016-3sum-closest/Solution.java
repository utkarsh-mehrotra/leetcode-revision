import java.util.Arrays;

/**
 * LeetCode 16. 3Sum Closest
 * Approach: Sort, then fix the smallest element of each triple and run
 * the same two-pointer converging scan as 3Sum on the remainder --
 * instead of looking for an exact zero sum, track whichever triple sum
 * seen so far is closest to target, and steer the pointers toward it
 * (move left up if the sum is too small, right down if too large).
 * Time: O(n²) | Space: O(n) sort, O(1) extra
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                if (sum == target) return sum;
                if (sum < target) left++;
                else right--;
            }
        }
        return closest;
    }
}
