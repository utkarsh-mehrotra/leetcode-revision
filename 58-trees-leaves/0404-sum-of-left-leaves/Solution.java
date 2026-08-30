/**
 * LeetCode 404. Sum of Left Leaves
 * Approach: DFS that tracks whether the current node is reached as a
 * left child. When a left-child leaf is found, add its value; otherwise
 * keep descending into both children.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return isLeft ? node.val : 0;
        }
        return dfs(node.left, true) + dfs(node.right, false);
    }
}
