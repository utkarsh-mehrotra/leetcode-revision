/**
 * LeetCode 376. Wiggle Subsequence
 * Approach: Track the longest wiggle subsequence ending in an "up" move and
 * one ending in a "down" move; each new element extends exactly one of them.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
