/**
 * LeetCode 143. Reorder List
 * Approach: Three classic linked-list two-pointer techniques chained
 * together: (1) slow/fast pointers find the middle, splitting the list
 * in two; (2) the second half is reversed in place with a prev/curr
 * pointer walk; (3) the two halves are merged by alternately splicing
 * one node from each, which produces exactly the required
 * first-last-second-secondlast-... order.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHead = slow.next;
        slow.next = null;
        ListNode secondReversed = reverse(secondHead);

        ListNode first = head, second = secondReversed;
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;
            first.next = second;
            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}
