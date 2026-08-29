import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 18. 4Sum
 * Approach: Sort, then fix the two smallest elements of each quadruple
 * with a nested loop and run the two-pointer converging scan on the
 * remainder, exactly extending 3Sum by one more fixed index. Duplicate
 * values are skipped at every one of the four positions. Sums are
 * accumulated in `long` since four 32-bit ints can overflow `int`.
 * Time: O(n³) | Space: O(n) sort, O(1) extra
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
