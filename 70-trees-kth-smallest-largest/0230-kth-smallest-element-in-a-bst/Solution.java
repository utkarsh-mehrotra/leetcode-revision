/**
 * LeetCode 230. Kth Smallest Element in a BST
 * Approach: In-order traversal visits BST values in sorted order, so
 * counting nodes as they're visited and stopping at the k-th one
 * directly gives the answer -- no need to materialize the full sorted
 * list.
 * Time: O(h + k) | Space: O(h) recursion stack
 */
class Solution {
    private int remaining;
    private int result;

    public int kthSmallest(TreeNode root, int k) {
        remaining = k;
        inorder(root);
        return result;
    }

    private void inorder(TreeNode node) {
        if (node == null || remaining == 0) return;
        inorder(node.left);
        if (remaining == 0) return;
        remaining--;
        if (remaining == 0) {
            result = node.val;
            return;
        }
        inorder(node.right);
    }
}
