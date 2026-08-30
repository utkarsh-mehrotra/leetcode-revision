import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1302. Deepest Leaves Sum
 * Approach: BFS level by level; after each level's queue is fully
 * drained, sum that level's values into a running "last level sum".
 * Once the queue empties, the last computed sum is the deepest level's
 * sum.
 * Time: O(n) | Space: O(n) queue
 */
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int levelSum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            levelSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return levelSum;
    }
}
