import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 95. Unique Binary Search Trees II
 * Approach: Top-down memoized recursion over (lo, hi) -- build every BST
 * whose in-order values are lo..hi by trying each value as the root and
 * combinatorially pairing every left subtree (built from lo..root-1) with
 * every right subtree (built from root+1..hi). The same (lo, hi) range is
 * requested repeatedly from different parent recursions, so it's cached
 * (and the built subtree instances are safely shared across the parent
 * trees that reuse them, since they're never mutated after construction).
 * Time: O(Catalan(n) * n) | Space: O(Catalan(n) * n)
 */
class Solution {
    private List<TreeNode>[][] dp;

    @SuppressWarnings("unchecked")
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        dp = new List[n + 2][n + 2];
        return build(1, n);
    }

    private List<TreeNode> build(int lo, int hi) {
        if (lo > hi) {
            List<TreeNode> single = new ArrayList<>();
            single.add(null);
            return single;
        }
        if (dp[lo][hi] != null) return dp[lo][hi];
        List<TreeNode> result = new ArrayList<>();
        for (int root = lo; root <= hi; root++) {
            List<TreeNode> lefts = build(lo, root - 1);
            List<TreeNode> rights = build(root + 1, hi);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode(root);
                    node.left = left;
                    node.right = right;
                    result.add(node);
                }
            }
        }
        dp[lo][hi] = result;
        return result;
    }
}
