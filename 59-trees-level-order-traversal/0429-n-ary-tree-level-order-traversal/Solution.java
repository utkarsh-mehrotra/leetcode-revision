import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 429. N-ary Tree Level Order Traversal
 * Approach: Same level-order BFS shape as the binary-tree version, but
 * each node offers its whole children list instead of at most two
 * children.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                if (node.children != null) queue.addAll(node.children);
            }
            result.add(level);
        }
        return result;
    }
}
