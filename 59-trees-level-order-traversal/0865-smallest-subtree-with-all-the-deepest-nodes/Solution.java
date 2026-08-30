/**
 * LeetCode 865. Smallest Subtree with all the Deepest Nodes
 * Approach: Post-order DFS returning {node, depth} -- when the left and
 * right subtrees report equal depth, the current node is the smallest
 * subtree containing all deepest nodes on both sides; otherwise
 * propagate up whichever side is deeper. (Same shape as LeetCode 1123,
 * a duplicate of this problem under a different title.)
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return deepest(root).node;
    }

    private Result deepest(TreeNode node) {
        if (node == null) return new Result(null, 0);
        Result left = deepest(node.left);
        Result right = deepest(node.right);
        if (left.depth == right.depth) return new Result(node, left.depth + 1);
        return left.depth > right.depth
            ? new Result(left.node, left.depth + 1)
            : new Result(right.node, right.depth + 1);
    }

    private static class Result {
        TreeNode node;
        int depth;
        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
