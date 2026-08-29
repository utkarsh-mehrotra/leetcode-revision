/**
 * LeetCode 61. Rotate List
 * Approach: Two pointers with a fixed gap, extended to a circular list.
 * First find the length and link the tail back to the head, forming a
 * ring. The new tail sits (length - k % length - 1) steps from the old
 * head; walk there and break the ring at the following node, which
 * becomes the new head.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        tail.next = head; // form a ring

        int stepsToNewTail = length - (k % length) - 1;
        ListNode newTail = head;
        for (int i = 0; i < stepsToNewTail; i++) newTail = newTail.next;

        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }
}
