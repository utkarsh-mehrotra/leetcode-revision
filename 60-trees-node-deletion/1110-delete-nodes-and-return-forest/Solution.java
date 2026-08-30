import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 1110. Delete Nodes And Return Forest
 * Approach: Post-order DFS carrying whether the current node's parent
 * was just deleted (i.e. whether this node is currently a root). A node
 * that is a root and not itself deleted starts a new tree in the
 * result. Deleted nodes detach from their parent (returning null) but
 * still recurse into their children first, so surviving subtrees become
 * new roots.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = new HashSet<>();
        for (int val : to_delete) toDelete.add(val);
        List<TreeNode> forest = new ArrayList<>();
        dfs(root, true, toDelete, forest);
        return forest;
    }

    private TreeNode dfs(TreeNode node, boolean isRoot, Set<Integer> toDelete, List<TreeNode> forest) {
        if (node == null) return null;
        boolean deleted = toDelete.contains(node.val);
        if (isRoot && !deleted) forest.add(node);
        node.left = dfs(node.left, deleted, toDelete, forest);
        node.right = dfs(node.right, deleted, toDelete, forest);
        return deleted ? null : node;
    }
}
