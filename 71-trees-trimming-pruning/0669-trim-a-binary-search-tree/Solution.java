/**
 * LeetCode 669. Trim a Binary Search Tree
 * Approach: Use BST ordering to skip whole subtrees -- if the current
 * node's value is below low, everything in its left subtree is also too
 * small, so trim by recursing into (and returning) the trimmed right
 * subtree directly; symmetric for values above high. Otherwise keep the
 * node and trim both children.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) return trimBST(root.right, low, high);
        if (root.val > high) return trimBST(root.left, low, high);
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
