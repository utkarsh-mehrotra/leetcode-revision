/**
 * LeetCode 82. Remove Duplicates from Sorted List II
 * Approach: A trailing pointer `prev` (starting at a dummy node ahead of
 * head) and a scanning pointer `node`. Whenever `node` starts a run of
 * one or more repeated values, a lookahead pointer walks to the end of
 * that run; if the run had more than one node, `prev.next` skips the
 * WHOLE run (every copy of that value is removed, not just the extras),
 * otherwise `prev` advances normally to keep the lone node.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy, node = head;

        while (node != null) {
            if (node.next != null && node.next.val == node.val) {
                int dupVal = node.val;
                while (node != null && node.val == dupVal) node = node.next;
                prev.next = node;
            } else {
                prev = node;
                node = node.next;
            }
        }
        return dummy.next;
    }
}
