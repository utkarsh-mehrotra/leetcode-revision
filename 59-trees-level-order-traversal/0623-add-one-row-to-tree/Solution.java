import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 623. Add One Row to Tree
 * Approach: If the target depth is 1, the whole tree becomes the new
 * row's right (or rather sole) child and a fresh root is returned.
 * Otherwise BFS down to depth-1: for every node found there, splice a
 * new node in between it and each existing child, hanging the old
 * subtree off the new node's matching side.
 * Time: O(n) | Space: O(n) queue
 */
class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty() && level < depth - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            level++;
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode newLeft = new TreeNode(val);
            newLeft.left = node.left;
            node.left = newLeft;
            TreeNode newRight = new TreeNode(val);
            newRight.right = node.right;
            node.right = newRight;
        }
        return root;
    }
}
