/**
 * LeetCode 450. Delete Node in a BST
 * Approach: Navigate down using BST ordering to find the target. A leaf
 * or single-child node is spliced out directly. A two-child node is
 * replaced by its in-order successor (the minimum of its right
 * subtree), whose original position is then deleted from that same
 * right subtree -- keeping the BST property intact throughout.
 * Time: O(h) | Space: O(h) recursion stack
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode successor = root.right;
            while (successor.left != null) successor = successor.left;
            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);
        }
        return root;
    }
}
