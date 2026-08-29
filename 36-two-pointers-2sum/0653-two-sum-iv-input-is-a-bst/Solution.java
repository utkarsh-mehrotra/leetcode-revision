import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 653. Two Sum IV - Input is a BST
 * Approach: An in-order traversal of a BST yields a sorted array, which
 * turns this back into Two Sum II: converge two pointers from both ends
 * of that sorted list looking for a pair summing to k.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> sorted = new ArrayList<>();
        inorder(root, sorted);

        int left = 0, right = sorted.size() - 1;
        while (left < right) {
            int sum = sorted.get(left) + sorted.get(right);
            if (sum == k) return true;
            if (sum < k) left++;
            else right--;
        }
        return false;
    }

    private void inorder(TreeNode node, List<Integer> out) {
        if (node == null) return;
        inorder(node.left, out);
        out.add(node.val);
        inorder(node.right, out);
    }
}
