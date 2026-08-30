/**
 * LeetCode 100. Same Tree
 * Approach: Recursive structural comparison -- both null is a match,
 * exactly one null or differing values is a mismatch, otherwise recurse
 * into both left and right pairs.
 * Time: O(min(n1, n2)) | Space: O(min(n1, n2)) recursion stack
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
