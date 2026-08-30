import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 1932. Merge BSTs to Create Single BST
 * Approach: Count how many times each value appears as a genuine leaf
 * (both children null) across all input trees, and index every tree by
 * its root value. Exactly one tree's root must never appear as a leaf
 * anywhere -- that one becomes the final tree's root. Merge top-down:
 * whenever a leaf's value matches another tree's root, splice that
 * tree in and keep merging into it. The merge only succeeds if every
 * other tree gets consumed this way and the resulting structure is a
 * valid BST (checked via a single in-order bounds pass).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> rootByValue = new HashMap<>();
        Map<Integer, Integer> leafCount = new HashMap<>();
        for (TreeNode root : trees) rootByValue.put(root.val, root);
        for (TreeNode root : trees) countLeaves(root, leafCount);

        TreeNode finalRoot = null;
        for (TreeNode root : trees) {
            if (leafCount.getOrDefault(root.val, 0) == 0) {
                if (finalRoot != null) return null; // more than one candidate root
                finalRoot = root;
            }
        }
        if (finalRoot == null) return null;

        rootByValue.remove(finalRoot.val);
        TreeNode merged = merge(finalRoot, rootByValue);
        if (!rootByValue.isEmpty()) return null; // some tree never got attached
        return isValidBST(merged, null, null) ? merged : null;
    }

    private void countLeaves(TreeNode node, Map<Integer, Integer> leafCount) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            leafCount.merge(node.val, 1, Integer::sum);
            return;
        }
        countLeaves(node.left, leafCount);
        countLeaves(node.right, leafCount);
    }

    private TreeNode merge(TreeNode node, Map<Integer, TreeNode> rootByValue) {
        if (node == null) return null;
        if (node.left == null && node.right == null && rootByValue.containsKey(node.val)) {
            return merge(rootByValue.remove(node.val), rootByValue);
        }
        node.left = merge(node.left, rootByValue);
        node.right = merge(node.right, rootByValue);
        return node;
    }

    private boolean isValidBST(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;
        if ((lower != null && node.val <= lower) || (upper != null && node.val >= upper)) return false;
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
}
