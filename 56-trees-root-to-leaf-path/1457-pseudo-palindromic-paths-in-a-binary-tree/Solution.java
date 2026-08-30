/**
 * LeetCode 1457. Pseudo-Palindromic Paths in a Binary Tree
 * Approach: A root-to-leaf path can be rearranged into a palindrome iff
 * at most one digit (1-9) has odd frequency. Track digit parity as a
 * bitmask, flipping bit (val-1) at each node; at a leaf, the path is
 * pseudo-palindromic iff the mask has at most one bit set (mask & (mask
 * - 1) == 0, which also holds for mask == 0).
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private int count = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, 0);
        return count;
    }

    private void dfs(TreeNode node, int mask) {
        if (node == null) return;
        mask ^= (1 << (node.val - 1));
        if (node.left == null && node.right == null) {
            if ((mask & (mask - 1)) == 0) count++;
            return;
        }
        dfs(node.left, mask);
        dfs(node.right, mask);
    }
}
