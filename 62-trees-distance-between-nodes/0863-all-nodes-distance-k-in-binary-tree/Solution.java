import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 863. All Nodes Distance K in Binary Tree
 * Approach: A binary tree only exposes downward links, but "distance k"
 * needs to travel upward too, so first DFS to record each node's parent.
 * Then BFS from target treating left, right, and parent all as equal
 * neighbors (a plain graph BFS), stopping once k levels have expanded --
 * whatever remains in the frontier is exactly the answer.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        buildParents(root, null, parent);

        Set<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(target);
        visited.add(target);
        int depth = 0;
        while (!queue.isEmpty() && depth < k) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                for (TreeNode neighbor : new TreeNode[]{node.left, node.right, parent.get(node)}) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
            depth++;
        }

        List<Integer> result = new ArrayList<>();
        for (TreeNode node : queue) result.add(node.val);
        return result;
    }

    private void buildParents(TreeNode node, TreeNode parentNode, Map<TreeNode, TreeNode> parent) {
        if (node == null) return;
        parent.put(node, parentNode);
        buildParents(node.left, node, parent);
        buildParents(node.right, node, parent);
    }
}
