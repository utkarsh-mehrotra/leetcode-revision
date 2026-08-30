/**
 * LeetCode 951. Flip Equivalent Binary Trees
 * Approach: Recursive structural comparison that allows a node's two
 * children to be matched either in original order or swapped -- two
 * trees are flip-equivalent iff their roots match and (left1~left2 &&
 * right1~right2) OR (left1~right2 && right1~left2) holds recursively.
 * Time: O(min(n1, n2)) | Space: O(min(n1, n2)) recursion stack
 */
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null || root1.val != root2.val) return false;
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
            || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
