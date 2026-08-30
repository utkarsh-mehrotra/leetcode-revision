/**
 * LeetCode 783. Minimum Distance Between BST Nodes
 * Approach: An in-order traversal of a BST visits values in sorted
 * order, so the minimum difference between any two node values must
 * occur between some pair of consecutive in-order values. Track the
 * previously visited value and update a running minimum as the
 * traversal proceeds.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private Integer prev = null;
    private int minDiff = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
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
