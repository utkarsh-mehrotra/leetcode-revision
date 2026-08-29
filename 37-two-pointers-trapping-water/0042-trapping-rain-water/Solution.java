/**
 * LeetCode 42. Trapping Rain Water
 * Approach: Two pointers converging from both ends, tracking the tallest
 * wall seen so far from each side. The water trapped above the shorter
 * side's pointer is fully determined by its own running max (the taller
 * side is guaranteed to bound it on the other end), so whichever side is
 * currently shorter can be resolved and advanced immediately.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }
}
