/**
 * LeetCode 86. Partition List
 * Approach: Split & merge -- build two separate chains with their own
 * tail pointers while making a single pass over the list (a "less than
 * x" chain and a "greater or equal" chain), then merge them by simply
 * splicing the second chain onto the end of the first. This preserves
 * each original chain's relative order, which a single in-place
 * partition swap could not guarantee.
 * Time: O(n) | Space: O(1) (excluding the two dummy nodes)
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        ListNode lessTail = lessDummy, greaterTail = greaterDummy;

        while (head != null) {
            if (head.val < x) {
                lessTail.next = head;
                lessTail = lessTail.next;
            } else {
                greaterTail.next = head;
                greaterTail = greaterTail.next;
            }
            head = head.next;
        }

        greaterTail.next = null;
        lessTail.next = greaterDummy.next;
        return lessDummy.next;
    }
}
