import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 437. Path Sum III
 * Approach: DFS with a running prefix sum from the root plus a
 * frequency map of every prefix sum seen on the current root-to-node
 * path. At each node, the number of valid downward paths ending here
 * equals count[prefixSum - targetSum]. The current node's prefix sum is
 * added before recursing and removed on the way back up (backtracking),
 * so the map only ever reflects the active path.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0L, 1);
        return dfs(root, 0L, targetSum, prefixCount);
    }

    private int dfs(TreeNode node, long prefixSum, int targetSum, Map<Long, Integer> prefixCount) {
        if (node == null) return 0;
        prefixSum += node.val;
        int count = prefixCount.getOrDefault(prefixSum - targetSum, 0);
        prefixCount.merge(prefixSum, 1, Integer::sum);
        count += dfs(node.left, prefixSum, targetSum, prefixCount);
        count += dfs(node.right, prefixSum, targetSum, prefixCount);
        prefixCount.merge(prefixSum, -1, Integer::sum);
        return count;
    }
}
