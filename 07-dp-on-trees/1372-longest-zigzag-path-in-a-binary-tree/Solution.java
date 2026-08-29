/**
 * LeetCode 1372. Longest ZigZag Path in a Binary Tree
 * Approach: Post-order recursion returning the longest zigzag reachable
 * from this node when the NEXT step must go left, and when it must go
 * right (a missing child contributes a sentinel of -1, so "+1" correctly
 * yields 0 -- meaning the path simply stops here). Each node is visited
 * once (no overlapping subproblems), so no explicit memo table is needed.
 * Time: O(n) | Space: O(n) recursion stack
 */
class Solution {
    private int best = 0;

    public int longestZigZag(TreeNode root) {
        solve(root);
        return best;
    }

    // Returns {longest path starting by going left, longest path starting by going right}.
    private int[] solve(TreeNode node) {
        if (node == null) return new int[]{-1, -1};
        int[] left = solve(node.left);
        int[] right = solve(node.right);
        int goLeft = left[1] + 1;   // step left, then must alternate to right next
        int goRight = right[0] + 1; // step right, then must alternate to left next
        best = Math.max(best, Math.max(goLeft, goRight));
        return new int[]{goLeft, goRight};
    }
}
