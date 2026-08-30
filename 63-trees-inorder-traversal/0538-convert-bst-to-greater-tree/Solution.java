/**
 * LeetCode 538. Convert BST to Greater Tree
 * Approach: Reverse in-order traversal (right, node, left) visits values
 * from largest to smallest, so a running sum accumulated in that order
 * is exactly "sum of everything greater than the current node" by the
 * time each node is processed.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private int runningSum = 0;

    public TreeNode convertBST(TreeNode root) {
        runningSum = 0;
        reverseInorder(root);
        return root;
    }

    private void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        runningSum += node.val;
        node.val = runningSum;
        reverseInorder(node.left);
    }
}
