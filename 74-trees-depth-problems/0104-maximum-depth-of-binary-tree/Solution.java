/**
 * LeetCode 104. Maximum Depth of Binary Tree
 * Approach: Recursive post-order -- a subtree's depth is 1 plus the
 * larger of its two children's depths, bottoming out at 0 for null.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
