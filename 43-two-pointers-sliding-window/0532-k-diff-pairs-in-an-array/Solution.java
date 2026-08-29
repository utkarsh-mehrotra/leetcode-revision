import java.util.Arrays;

/**
 * LeetCode 532. K-diff Pairs in an Array
 * Approach: Sort, then two pointers (left, right) crawl forward together
 * (caterpillar-style, never resetting). If the gap is too small, advance
 * right; too large, advance left; exactly k means a valid pair, counted
 * once, after which left advances past every duplicate of its own value
 * so the same (value, value+k) pair is never recounted -- correct for
 * k == 0 too, since sorted duplicates sit adjacent and this still finds
 * exactly one pair per repeated value.
 * Time: O(n log n) | Space: O(1) extra
 */
class Solution {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = 1, count = 0;

        while (left < n && right < n) {
            if (left == right || nums[right] - nums[left] < k) {
                right++;
            } else if (nums[right] - nums[left] > k) {
                left++;
            } else {
                count++;
                left++;
                while (left < n && nums[left] == nums[left - 1]) left++;
            }
        }
        return count;
    }
}
