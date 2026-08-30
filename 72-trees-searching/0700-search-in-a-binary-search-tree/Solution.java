/**
 * LeetCode 700. Search in a Binary Search Tree
 * Approach: Iterative BST navigation -- move left or right based on how
 * val compares to the current node, stopping as soon as a match or a
 * null is reached.
 * Time: O(h) | Space: O(1)
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null && node.val != val) {
            node = val < node.val ? node.left : node.right;
        }
        return node;
    }
}
