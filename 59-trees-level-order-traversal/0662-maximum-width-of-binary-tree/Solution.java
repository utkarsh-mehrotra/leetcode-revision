import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 662. Maximum Width of Binary Tree
 * Approach: Level-order BFS pairing each node with a position index as
 * if the tree were a complete binary tree stored in an array (left =
 * 2*i, right = 2*i+1). A level's width is (last index - first index +
 * 1). Indices are re-based to the first index of each level before
 * recursing to avoid overflow on deep, sparse trees.
 * Time: O(n) | Space: O(n) queue
 */
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<Long> indexQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        indexQueue.offer(0L);
        int maxWidth = 0;
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            long first = indexQueue.peek();
            long last = first;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                long index = indexQueue.poll() - first;
                last = index;
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    indexQueue.offer(2 * index);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    indexQueue.offer(2 * index + 1);
                }
            }
            maxWidth = Math.max(maxWidth, (int) (last + 1));
        }
        return maxWidth;
    }
}
