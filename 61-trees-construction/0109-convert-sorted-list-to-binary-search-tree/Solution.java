import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 109. Convert Sorted List to Binary Search Tree
 * Approach: Dump the linked list into an array in one pass (random
 * access is what makes the balanced-middle-split trick from the sorted
 * array version fast), then recurse exactly as in LeetCode 108.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) values.add(cur.val);
        return build(values, 0, values.size() - 1);
    }

    private TreeNode build(List<Integer> values, int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(values.get(mid));
        node.left = build(values, lo, mid - 1);
        node.right = build(values, mid + 1, hi);
        return node;
    }
}
