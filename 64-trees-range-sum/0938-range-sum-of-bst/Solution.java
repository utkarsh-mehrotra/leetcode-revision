/**
 * LeetCode 938. Range Sum of BST
 * Approach: Prune using BST ordering -- if the current value is below
 * low, only the right subtree can contain values in range; if above
 * high, only the left subtree can; otherwise include this node and
 * recurse into both sides.
 * Time: O(n) worst case, better on balanced/pruned trees | Space: O(h)
 */
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val < low) return rangeSumBST(root.right, low, high);
        if (root.val > high) return rangeSumBST(root.left, low, high);
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}
