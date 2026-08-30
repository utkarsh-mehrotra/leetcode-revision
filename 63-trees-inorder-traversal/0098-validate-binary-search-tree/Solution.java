/**
 * LeetCode 98. Validate Binary Search Tree
 * Approach: Recursion carrying an open (lower, upper) bound inherited
 * from ancestors -- every node must strictly fall inside it, and each
 * child call tightens the bound on whichever side the node was reached
 * from. This catches violations from any ancestor, not just the direct
 * parent, which a naive left.val < node.val < right.val check would
 * miss.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Long lower, Long upper) {
        if (node == null) return true;
        if ((lower != null && node.val <= lower) || (upper != null && node.val >= upper)) return false;
        return validate(node.left, lower, (long) node.val) && validate(node.right, (long) node.val, upper);
    }
}
