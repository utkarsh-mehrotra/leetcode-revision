/**
 * LeetCode 19. Remove Nth Node From End of List
 * Approach: Fast/slow pointers with a fixed head start. Fast advances n
 * steps first, opening a gap of exactly n nodes; then both advance
 * together until fast falls off the end, leaving slow immediately before
 * the node to remove. A dummy node ahead of head lets this uniformly
 * handle removing the head itself.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n; i++) fast = fast.next;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
