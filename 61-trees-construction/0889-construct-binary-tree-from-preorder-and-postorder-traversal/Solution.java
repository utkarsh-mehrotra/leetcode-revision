import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 889. Construct Binary Tree from Preorder and Postorder Traversal
 * Approach: preorder[0] is always the root, and preorder[1] (if present)
 * is always the root of the left subtree. Looking up that value's index
 * in postorder gives the left subtree's size, since postorder finishes
 * the entire left subtree before touching the right one. Split both
 * arrays at that boundary and recurse.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] preorder, postorder;
    private Map<Integer, Integer> postIndex;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        this.postorder = postorder;
        postIndex = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) postIndex.put(postorder[i], i);
        return build(0, preorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode build(int preLo, int preHi, int postLo, int postHi) {
        TreeNode node = new TreeNode(preorder[preLo]);
        if (preLo == preHi) return node;
        int leftRootVal = preorder[preLo + 1];
        int leftSize = postIndex.get(leftRootVal) - postLo + 1;
        node.left = build(preLo + 1, preLo + leftSize, postLo, postLo + leftSize - 1);
        if (leftSize < preHi - preLo) {
            node.right = build(preLo + leftSize + 1, preHi, postLo + leftSize, postHi - 1);
        }
        return node;
    }
}
