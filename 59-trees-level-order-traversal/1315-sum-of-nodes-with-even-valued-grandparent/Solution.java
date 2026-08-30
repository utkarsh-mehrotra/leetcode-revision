/**
 * LeetCode 1315. Sum of Nodes with Even-Valued Grandparent
 * Approach: DFS carrying the parent's and grandparent's values down the
 * path. A node contributes to the sum whenever its grandparent's value
 * is even (0 counts as even, so the sentinel -1 is used for "no
 * grandparent yet" instead).
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        sum = 0;
        dfs(root, -1, -1);
        return sum;
    }

    private void dfs(TreeNode node, int parentVal, int grandparentVal) {
        if (node == null) return;
        if (grandparentVal != -1 && grandparentVal % 2 == 0) sum += node.val;
        dfs(node.left, node.val, parentVal);
        dfs(node.right, node.val, parentVal);
    }
}
