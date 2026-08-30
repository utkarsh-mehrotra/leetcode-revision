/**
 * LeetCode 111. Minimum Depth of Binary Tree
 * Approach: Recursive post-order, but a node with only one child must
 * follow that child rather than treating the missing side as depth 0 --
 * otherwise a long chain with one-sided nodes near the root would
 * wrongly report depth 1. Only a true leaf (both children null)
 * legitimately bottoms out the recursion.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}
