import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1161. Maximum Level Sum of a Binary Tree
 * Approach: Level-order BFS summing each level while draining its queue
 * snapshot, tracking the best sum and its 1-indexed level number.
 * Time: O(n) | Space: O(n) queue
 */
class Solution {
    public int maxLevelSum(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0, bestLevel = 0;
        long bestSum = Long.MIN_VALUE;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (sum > bestSum) {
                bestSum = sum;
                bestLevel = level;
            }
        }
        return bestLevel;
    }
}
