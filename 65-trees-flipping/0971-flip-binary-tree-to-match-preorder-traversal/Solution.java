import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 971. Flip Binary Tree To Match Preorder Traversal
 * Approach: Walk the tree in pre-order alongside an index into voyage.
 * A mismatch at the current node fails immediately. If the left child's
 * value doesn't match the next expected voyage entry (but the right
 * child's does), flip that node's children and record it, then continue
 * matching in the new order; if neither child matches, the voyage is
 * unreachable.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int index = 0;
    private boolean possible = true;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        index = 0;
        possible = true;
        List<Integer> flipped = new ArrayList<>();
        dfs(root, voyage, flipped);
        return possible ? flipped : List.of(-1);
    }

    private void dfs(TreeNode node, int[] voyage, List<Integer> flipped) {
        if (node == null || !possible) return;
        if (node.val != voyage[index++]) {
            possible = false;
            return;
        }
        if (node.left != null && index < voyage.length && node.left.val != voyage[index]) {
            flipped.add(node.val);
            dfs(node.right, voyage, flipped);
            dfs(node.left, voyage, flipped);
        } else {
            dfs(node.left, voyage, flipped);
            dfs(node.right, voyage, flipped);
        }
    }
}
