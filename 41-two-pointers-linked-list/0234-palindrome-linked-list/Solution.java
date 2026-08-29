/**
 * LeetCode 234. Palindrome Linked List
 * Approach: Slow/fast pointers find the middle, the second half is
 * reversed in place, and then two pointers walk the first half and the
 * reversed second half in lockstep comparing values -- an O(1)-space
 * variant of the converging two-pointer palindrome check adapted to a
 * singly linked list (which can't be indexed from the back directly).
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverse(slow);
        ListNode first = head, second = secondHalf;
        boolean result = true;
        while (second != null) {
            if (first.val != second.val) {
                result = false;
                break;
            }
            first = first.next;
            second = second.next;
        }
        return result;
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
