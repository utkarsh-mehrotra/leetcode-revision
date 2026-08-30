import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 993. Cousins in Binary Tree
 * Approach: Level-order BFS tracking each node's parent alongside it.
 * Two nodes are cousins iff they're found at the same depth (same BFS
 * level) but with different parents -- checked by scanning each level
 * for both targets before moving to the next.
 * Time: O(n) | Space: O(n) queue
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<TreeNode> parentQueue = new ArrayDeque<>();
        queue.offer(root);
        parentQueue.offer(null);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode xParent = null, yParent = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode parent = parentQueue.poll();
                if (node.val == x) xParent = parent;
                if (node.val == y) yParent = parent;
                if (node.left != null) {
                    queue.offer(node.left);
                    parentQueue.offer(node);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    parentQueue.offer(node);
                }
            }
            if (xParent != null && yParent != null) return xParent != yParent;
            if (xParent != null || yParent != null) return false;
        }
        return false;
    }
}
