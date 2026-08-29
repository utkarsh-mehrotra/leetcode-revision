/**
 * LeetCode 142. Linked List Cycle II
 * Approach: Floyd's slow/fast pointers find a meeting point inside the
 * cycle (if any). From there, the classic distance argument shows that
 * resetting one pointer to head and advancing BOTH pointers one step at
 * a time makes them meet exactly at the cycle's start node.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
