/**
 * LeetCode 235. Lowest Common Ancestor of a Binary Search Tree
 * Approach: Use the BST ordering to navigate directly toward the split
 * point -- if both p and q are smaller than the current node, the LCA is
 * in the left subtree; if both are larger, it's in the right subtree;
 * otherwise the current node is the split point (the LCA).
 * Time: O(h) | Space: O(1)
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (p.val < node.val && q.val < node.val) {
                node = node.left;
            } else if (p.val > node.val && q.val > node.val) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }
}
