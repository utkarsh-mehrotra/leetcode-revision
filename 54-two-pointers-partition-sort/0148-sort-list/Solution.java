/**
 * LeetCode 148. Sort List
 * Approach: Classic divide & conquer merge sort adapted to a linked
 * list. Divide: a slow/fast pointer finds the middle and the list is cut
 * into two halves. Conquer: each half is sorted recursively. Merge: the
 * two sorted halves are combined with a two-pointer merge walk, always
 * splicing whichever head is smaller. Achieves O(n log n) time with only
 * O(log n) extra space (the recursion stack), unlike an array-based sort
 * which would need O(n) auxiliary space to hold the list's values.
 * Time: O(n log n) | Space: O(log n) recursion stack
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalf = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(secondHalf);
        return merge(left, right);
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }
}
