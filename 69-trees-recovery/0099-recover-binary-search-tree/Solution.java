/**
 * LeetCode 99. Recover Binary Search Tree
 * Approach: In-order traversal of a valid BST is strictly increasing, so
 * exactly two values were swapped iff the sequence has one or two
 * places where it dips (prev > current). Adjacent swapped nodes produce
 * one dip (first = prev, second = current there); non-adjacent swapped
 * nodes produce two dips (first = prev at the first dip, second =
 * current at the second dip). Swap their values back at the end.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private TreeNode prev, first, second;

    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev != null && prev.val > node.val) {
            if (first == null) first = prev;
            second = node;
        }
        prev = node;
        inorder(node.right);
    }
}
