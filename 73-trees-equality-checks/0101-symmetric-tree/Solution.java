/**
 * LeetCode 101. Symmetric Tree
 * Approach: A tree is symmetric iff its left and right subtrees are
 * mirror images of each other -- recursively check that each pair's
 * outer children (left.left vs right.right) and inner children
 * (left.right vs right.left) mirror-match.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
