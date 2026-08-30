/**
 * LeetCode 1080. Insufficient Nodes in Root to Leaf Paths
 * Approach: Post-order DFS carrying the sum accumulated from the root.
 * A leaf is insufficient (pruned) if sum + leaf.val < limit. An internal
 * node is pruned exactly when both of its children end up pruned --
 * meaning every path through it was insufficient -- otherwise it keeps
 * whichever children survived.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return prune(root, 0, limit);
    }

    private TreeNode prune(TreeNode node, int sumSoFar, int limit) {
        if (node == null) return null;
        sumSoFar += node.val;
        if (node.left == null && node.right == null) {
            return sumSoFar < limit ? null : node;
        }
        node.left = prune(node.left, sumSoFar, limit);
        node.right = prune(node.right, sumSoFar, limit);
        return (node.left == null && node.right == null) ? null : node;
    }
}
