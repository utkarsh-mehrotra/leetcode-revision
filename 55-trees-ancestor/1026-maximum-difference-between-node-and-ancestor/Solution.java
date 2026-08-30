/**
 * LeetCode 1026. Maximum Difference Between Node and Ancestor
 * Approach: Pre-order DFS carrying the min and max values seen so far on
 * the root-to-current path. At each node, the best possible |ancestor -
 * descendant| difference involving that node is against the running min
 * or max, so update a global best before recursing with the widened
 * range.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private int best = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return best;
    }

    private void dfs(TreeNode node, int curMin, int curMax) {
        if (node == null) return;
        best = Math.max(best, Math.max(Math.abs(node.val - curMin), Math.abs(node.val - curMax)));
        curMin = Math.min(curMin, node.val);
        curMax = Math.max(curMax, node.val);
        dfs(node.left, curMin, curMax);
        dfs(node.right, curMin, curMax);
    }
}
