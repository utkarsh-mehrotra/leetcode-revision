/**
 * LeetCode 1325. Delete Leaves With a Given Value
 * Approach: Post-order recursion -- prune both children first, then
 * check whether the current node has become a leaf matching target
 * (either it started as one, or both its children were just pruned
 * away). Pruning cascades upward this way in a single pass.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) return null;
        return root;
    }
}
