/**
 * LeetCode 337. House Robber III
 * Approach: Post-order tree recursion returning both "best loot rooted
 * here if we skip this node" and "...if we rob it". Each tree node is
 * visited exactly once (a tree has no overlapping subproblems the way an
 * array or graph can), so no separate memo table is needed -- the single
 * traversal already computes every subproblem exactly once.
 * Time: O(n) | Space: O(n) recursion stack
 */
class Solution {
    public int rob(TreeNode root) {
        int[] result = solve(root);
        return Math.max(result[0], result[1]);
    }

    // Returns {bestIfSkipped, bestIfRobbed} for the subtree rooted at node.
    private int[] solve(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] left = solve(node.left);
        int[] right = solve(node.right);
        int skipped = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        int robbed = node.val + left[0] + right[0];
        return new int[]{skipped, robbed};
    }
}
