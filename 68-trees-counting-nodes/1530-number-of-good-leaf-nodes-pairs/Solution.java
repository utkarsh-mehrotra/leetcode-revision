import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1530. Number of Good Leaf Nodes Pairs
 * Approach: Post-order DFS where each call returns the list of
 * distances from the current node down to every leaf in its subtree.
 * At an internal node, every left-leaf/right-leaf combination whose
 * distances sum to (through this node) <= distance forms a good pair;
 * tally those, then merge the two lists (each shifted by 1 for the edge
 * up to this node) to return upward. Distances already >= the limit are
 * dropped since they can only grow further up.
 * Time: O(n * distance) | Space: O(n) recursion + leaf-distance lists
 */
class Solution {
    private int distance;
    private int pairCount = 0;

    public int countPairs(TreeNode root, int distance) {
        this.distance = distance;
        pairCount = 0;
        dfs(root);
        return pairCount;
    }

    private List<Integer> dfs(TreeNode node) {
        if (node == null) return new ArrayList<>();
        if (node.left == null && node.right == null) {
            List<Integer> self = new ArrayList<>();
            self.add(0);
            return self;
        }
        List<Integer> leftLeaves = dfs(node.left);
        List<Integer> rightLeaves = dfs(node.right);
        for (int leftDist : leftLeaves) {
            for (int rightDist : rightLeaves) {
                if (leftDist + rightDist + 2 <= distance) pairCount++;
            }
        }
        List<Integer> merged = new ArrayList<>();
        for (int d : leftLeaves) if (d + 1 < distance) merged.add(d + 1);
        for (int d : rightLeaves) if (d + 1 < distance) merged.add(d + 1);
        return merged;
    }
}
