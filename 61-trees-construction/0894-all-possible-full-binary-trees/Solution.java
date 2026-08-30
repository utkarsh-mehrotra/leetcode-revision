import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 894. All Possible Full Binary Trees
 * Approach: A full binary tree with n nodes only exists for odd n (one
 * root plus a left/right split of the remaining n-1 nodes, which must
 * itself split evenly into two odd-sized full binary trees). Memoized
 * recursion on n builds every combination: for each odd split (i,
 * n-1-i), pair every possible left subtree with every possible right
 * subtree under a fresh root.
 * Time: O(Catalan(n)) | Space: O(Catalan(n))
 */
class Solution {
    private final Map<Integer, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (memo.containsKey(n)) return memo.get(n);
        List<TreeNode> result = new ArrayList<>();
        if (n == 1) {
            result.add(new TreeNode(0));
        } else if (n % 2 == 1) {
            for (int leftSize = 1; leftSize < n; leftSize += 2) {
                int rightSize = n - 1 - leftSize;
                for (TreeNode left : allPossibleFBT(leftSize)) {
                    for (TreeNode right : allPossibleFBT(rightSize)) {
                        TreeNode root = new TreeNode(0);
                        root.left = left;
                        root.right = right;
                        result.add(root);
                    }
                }
            }
        }
        memo.put(n, result);
        return result;
    }
}
