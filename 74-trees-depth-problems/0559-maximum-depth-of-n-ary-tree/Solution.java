/**
 * LeetCode 559. Maximum Depth of N-ary Tree
 * Approach: Same shape as the binary-tree version -- a subtree's depth
 * is 1 plus the largest depth among all of its children (not just two).
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int deepestChild = 0;
        for (Node child : root.children) {
            deepestChild = Math.max(deepestChild, maxDepth(child));
        }
        return 1 + deepestChild;
    }
}
