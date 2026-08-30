/**
 * LeetCode 236. Lowest Common Ancestor of a Binary Tree
 * Approach: Post-order recursion -- a node is the LCA if p and q are
 * found in different subtrees (or the node itself is p or q and the
 * other target is found below it). Each call returns p, q, the LCA
 * itself once found, or null if neither target is below.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
}
