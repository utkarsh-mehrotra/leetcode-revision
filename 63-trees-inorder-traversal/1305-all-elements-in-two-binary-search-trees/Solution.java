import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1305. All Elements in Two Binary Search Trees
 * Approach: In-order traversal collects each BST's values in sorted
 * order, so merging the two resulting lists is the classic two-pointer
 * merge step of merge sort.
 * Time: O(m + n) | Space: O(m + n)
 */
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inorder(root1, list1);
        inorder(root2, list2);

        List<Integer> merged = new ArrayList<>(list1.size() + list2.size());
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) merged.add(list1.get(i++));
            else merged.add(list2.get(j++));
        }
        while (i < list1.size()) merged.add(list1.get(i++));
        while (j < list2.size()) merged.add(list2.get(j++));
        return merged;
    }

    private void inorder(TreeNode node, List<Integer> out) {
        if (node == null) return;
        inorder(node.left, out);
        out.add(node.val);
        inorder(node.right, out);
    }
}
