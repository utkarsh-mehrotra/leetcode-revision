import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1609. Even Odd Tree
 * Approach: Level-order BFS; even-indexed levels must be strictly
 * increasing odd values, odd-indexed levels must be strictly decreasing
 * even values. Validate each level against these two constraints while
 * draining its queue snapshot, comparing each node to the previous one
 * seen at that level.
 * Time: O(n) | Space: O(n) queue
 */
class Solution {
    public boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer prev = null;
            boolean evenLevel = level % 2 == 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                boolean oddValue = node.val % 2 != 0;
                if (evenLevel != oddValue) return false;
                if (prev != null) {
                    if (evenLevel && node.val <= prev) return false;
                    if (!evenLevel && node.val >= prev) return false;
                }
                prev = node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            level++;
        }
        return true;
    }
}
