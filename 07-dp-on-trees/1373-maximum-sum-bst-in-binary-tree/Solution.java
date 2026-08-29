/**
 * LeetCode 1373. Maximum Sum BST in Binary Tree
 * Approach: Post-order recursion returning {isBST, min, max, sum} for each
 * subtree -- a subtree is a valid BST only if both children are valid
 * BSTs whose value ranges fit strictly around this node's value. Each
 * node is visited once (no overlapping subproblems), so no explicit memo
 * table is needed; the best sum among valid BST subtrees is tracked as
 * we go.
 * Time: O(n) | Space: O(n) recursion stack
 */
class Solution {
    private int best = 0;

    public int maxSumBST(TreeNode root) {
        solve(root);
        return best;
    }

    // Returns {isBST (1/0), minVal, maxVal, sum}. A non-BST subtree's
    // min/max/sum are meaningless and never consulted by its caller.
    private int[] solve(TreeNode node) {
        if (node == null) return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] left = solve(node.left);
        int[] right = solve(node.right);

        boolean isBst = left[0] == 1 && right[0] == 1
            && node.val > left[2] && node.val < right[1];
        if (!isBst) return new int[]{0, 0, 0, 0};

        int sum = node.val + left[3] + right[3];
        best = Math.max(best, sum);
        int min = Math.min(node.val, left[1]);
        int max = Math.max(node.val, right[2]);
        return new int[]{1, min, max, sum};
    }
}
