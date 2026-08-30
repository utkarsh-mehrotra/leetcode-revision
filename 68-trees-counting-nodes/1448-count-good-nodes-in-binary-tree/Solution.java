/**
 * LeetCode 1448. Count Good Nodes in Binary Tree
 * Approach: DFS carrying the maximum value seen so far on the path from
 * the root. A node is "good" iff its value is at least that running
 * max, in which case it also becomes the new max passed to its
 * children.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;
        int count = node.val >= maxSoFar ? 1 : 0;
        maxSoFar = Math.max(maxSoFar, node.val);
        return count + dfs(node.left, maxSoFar) + dfs(node.right, maxSoFar);
    }
}
