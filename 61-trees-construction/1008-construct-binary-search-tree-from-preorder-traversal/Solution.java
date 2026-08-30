/**
 * LeetCode 1008. Construct Binary Search Tree from Preorder Traversal
 * Approach: Replay pre-order construction with an upper bound per call
 * -- the next value becomes the current subtree's root only while it's
 * still below the bound inherited from an ancestor, which is exactly
 * what BST ordering guarantees about a pre-order sequence.
 * Time: O(n) | Space: O(h) recursion stack
 */
class Solution {
    private int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int upperBound) {
        if (index == preorder.length || preorder[index] > upperBound) return null;
        TreeNode node = new TreeNode(preorder[index++]);
        node.left = build(preorder, node.val);
        node.right = build(preorder, upperBound);
        return node;
    }
}
