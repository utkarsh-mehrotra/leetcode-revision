/**
 * LeetCode 530. Minimum Absolute Difference in BST
 * Approach: Same idea as LeetCode 783 (an earlier duplicate of this
 * problem) -- an in-order traversal of a BST visits values in sorted
 * order, so the minimum absolute difference must occur between some
 * pair of consecutive in-order values.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private Integer prev = null;
    private int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        prev = null;
        minDiff = Integer.MAX_VALUE;
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev != null) minDiff = Math.min(minDiff, node.val - prev);
        prev = node.val;
        inorder(node.right);
    }
}
