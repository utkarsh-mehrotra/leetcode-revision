/**
 * LeetCode 141. Linked List Cycle
 * Approach: Floyd's slow/fast pointer cycle detection. Slow advances one
 * node per step, fast advances two; if a cycle exists, fast eventually
 * laps slow and they meet inside it. If fast reaches the end, there's no
 * cycle.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
