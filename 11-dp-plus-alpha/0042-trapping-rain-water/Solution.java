/**
 * LeetCode 42. Trapping Rain Water
 * Approach: Top-down memoized recursion -- leftMax(i)/rightMax(i) is the
 * tallest wall from the start through i / from i through the end. The
 * water held at i is bounded by the shorter of those two walls, minus
 * the ground itself.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] height;
    private int n;
    private Integer[] leftDp, rightDp;

    public int trap(int[] height) {
        this.height = height;
        this.n = height.length;
        if (n == 0) return 0;
        this.leftDp = new Integer[n];
        this.rightDp = new Integer[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += Math.min(leftMax(i), rightMax(i)) - height[i];
        }
        return total;
    }

    private int leftMax(int i) {
        if (leftDp[i] != null) return leftDp[i];
        int result = (i == 0) ? height[0] : Math.max(height[i], leftMax(i - 1));
        leftDp[i] = result;
        return result;
    }

    private int rightMax(int i) {
        if (rightDp[i] != null) return rightDp[i];
        int result = (i == n - 1) ? height[n - 1] : Math.max(height[i], rightMax(i + 1));
        rightDp[i] = result;
        return result;
    }
}
