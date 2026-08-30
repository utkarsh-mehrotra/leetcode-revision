import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 872. Leaf-Similar Trees
 * Approach: DFS both trees to collect their leaf-value sequences in
 * left-to-right order, then compare the two sequences for equality.
 * Time: O(n + m) | Space: O(n + m)
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        collectLeaves(root1, leaves1);
        collectLeaves(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    private void collectLeaves(TreeNode node, List<Integer> leaves) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return;
        }
        collectLeaves(node.left, leaves);
        collectLeaves(node.right, leaves);
    }
}
