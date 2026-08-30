import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 257. Binary Tree Paths
 * Approach: DFS building a "/"-free path string as it descends; when a
 * leaf is hit, the accumulated path is recorded. Backtracking is
 * implicit since each recursive call builds its own extended string
 * rather than mutating a shared buffer.
 * Time: O(n^2) worst case (string concatenation per leaf) | Space: O(n)
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) dfs(root, String.valueOf(root.val), paths);
        return paths;
    }

    private void dfs(TreeNode node, String path, List<String> paths) {
        if (node.left == null && node.right == null) {
            paths.add(path);
            return;
        }
        if (node.left != null) dfs(node.left, path + "->" + node.left.val, paths);
        if (node.right != null) dfs(node.right, path + "->" + node.right.val, paths);
    }
}
