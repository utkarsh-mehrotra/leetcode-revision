/**
 * LeetCode 897. Increasing Order Search Tree
 * Approach: In-order traversal (sorted order for a BST) while rewiring
 * every node into a right-only chain as it's visited -- a dummy head's
 * "current tail" pointer is advanced and re-linked at each step, so no
 * separate list is built and then converted.
 * Time: O(n) | Space: O(h) recursion stack (excluding output)
 */
class Solution {
    private TreeNode tail;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        tail = dummy;
        inorder(root);
        return dummy.right;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        node.left = null;
        tail.right = node;
        tail = node;
        inorder(node.right);
    }
}
