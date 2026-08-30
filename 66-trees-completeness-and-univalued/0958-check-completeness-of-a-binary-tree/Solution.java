import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 958. Check Completeness of a Binary Tree
 * Approach: BFS that enqueues null placeholders for missing children
 * too. A tree is complete iff, once the first null is dequeued, every
 * remaining entry in the queue is also null -- any real node appearing
 * after a gap means the tree isn't packed left-to-right.
 * Time: O(n) | Space: O(n) queue
 */
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean seenNull = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                seenNull = true;
                continue;
            }
            if (seenNull) return false;
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return true;
    }
}
