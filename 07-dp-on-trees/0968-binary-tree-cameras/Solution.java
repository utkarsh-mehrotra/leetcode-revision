/**
 * LeetCode 968. Binary Tree Cameras
 * Approach: Post-order recursion with a 3-state return per node -- 0 =
 * not covered, 1 = covered without a camera here, 2 = has a camera here.
 * A parent whose child reports "not covered" must place a camera; a
 * parent with any child reporting "has a camera" is covered for free.
 * Each node is visited once (no overlapping subproblems), so no explicit
 * memo table is needed.
 * Time: O(n) | Space: O(n) recursion stack
 */
class Solution {
    private int cameras = 0;

    public int minCameraCover(TreeNode root) {
        if (solve(root) == 0) cameras++; // root itself ended up uncovered
        return cameras;
    }

    private int solve(TreeNode node) {
        if (node == null) return 1; // treat absent children as already covered
        int left = solve(node.left);
        int right = solve(node.right);
        if (left == 0 || right == 0) {
            cameras++;
            return 2;
        }
        if (left == 2 || right == 2) return 1;
        return 0;
    }
}
