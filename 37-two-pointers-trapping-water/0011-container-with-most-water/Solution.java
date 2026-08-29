/**
 * LeetCode 11. Container With Most Water
 * Approach: Two pointers converging from both ends. The container width
 * only shrinks as the pointers move, so the only way area can improve is
 * if height increases -- meaning the shorter of the two current walls can
 * never be part of a better solution paired with anything further in
 * (every such pairing is width-limited by the same shorter wall or an
 * even shorter one), so it's always safe to advance the shorter side.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int best = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            best = Math.max(best, area);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return best;
    }
}
