/**
 * LeetCode 1367. Linked List in Binary Tree
 * Approach: Two nested recursions -- isSubPath checks every tree node as a
 * possible starting point, and matches verifies the list follows a
 * downward path from that starting node. No overlapping subproblems
 * (each tree node is a start-point candidate exactly once), so no memo
 * table is needed.
 * Time: O(treeNodes * listLength) | Space: O(tree height + list length)
 */
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) return false;
        return matches(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean matches(ListNode listNode, TreeNode treeNode) {
        if (listNode == null) return true;
        if (treeNode == null || treeNode.val != listNode.val) return false;
        return matches(listNode.next, treeNode.left) || matches(listNode.next, treeNode.right);
    }
}
