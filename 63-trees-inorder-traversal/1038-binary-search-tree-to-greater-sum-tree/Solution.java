/**
 * LeetCode 1038. Binary Search Tree to Greater Sum Tree
 * Approach: Identical to LeetCode 538 -- reverse in-order (right, node,
 * left) visits values largest-first, so a running sum accumulated along
 * that order gives each node the sum of every value greater than it.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private int runningSum = 0;

    public TreeNode bstToGst(TreeNode root) {
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
