/**
 * LeetCode 1339. Maximum Product of Splitted Binary Tree
 * Approach: Post-order recursion computes every subtree's sum in one pass
 * (each node visited once, no overlapping subproblems, so no explicit
 * memo table is needed). Removing the edge above a subtree splits the
 * tree into that subtree (sum s) and the rest (total - s); track the best
 * s * (total - s) seen while computing sums.
 * Time: O(n) | Space: O(n) recursion stack
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private long total;
    private long best;

    public int maxProduct(TreeNode root) {
        total = subtreeSum(root, false);
        best = 0;
        subtreeSum(root, true);
        return (int) (best % MOD);
    }

    private long subtreeSum(TreeNode node, boolean trackBest) {
        if (node == null) return 0;
        long sum = node.val + subtreeSum(node.left, trackBest) + subtreeSum(node.right, trackBest);
        if (trackBest) {
            best = Math.max(best, sum * (total - sum));
        }
        return sum;
    }
}
