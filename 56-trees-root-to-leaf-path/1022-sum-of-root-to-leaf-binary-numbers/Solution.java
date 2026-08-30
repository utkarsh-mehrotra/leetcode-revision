/**
 * LeetCode 1022. Sum of Root To Leaf Binary Numbers
 * Approach: DFS carrying the binary value accumulated so far (shift left
 * and OR in the current bit at each level). At a leaf, add the
 * accumulated value to the running total.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private int total = 0;

    public int sumRootToLeaf(TreeNode root) {
        total = 0;
        dfs(root, 0);
        return total;
    }

    private void dfs(TreeNode node, int value) {
        if (node == null) return;
        value = (value << 1) | node.val;
        if (node.left == null && node.right == null) {
            total += value;
            return;
        }
        dfs(node.left, value);
        dfs(node.right, value);
    }
}
