/**
 * LeetCode 965. Univalued Binary Tree
 * Approach: DFS comparing every node's value against the root's value;
 * short-circuits as soon as a mismatch is found.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root, root.val);
    }

    private boolean dfs(TreeNode node, int value) {
        if (node == null) return true;
        if (node.val != value) return false;
        return dfs(node.left, value) && dfs(node.right, value);
    }
}
