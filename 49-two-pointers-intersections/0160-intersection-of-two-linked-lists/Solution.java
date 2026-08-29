/**
 * LeetCode 160. Intersection of Two Linked Lists
 * Approach: Two pointers, one starting at each list's head, each walking
 * its own list and then switching to the OTHER list's head once it hits
 * the end. Both pointers travel lenA + lenB steps total before meeting,
 * which synchronizes them to arrive at the intersection point (or both
 * hit null simultaneously if there is none) without ever computing the
 * lengths explicitly.
 * Time: O(m+n) | Space: O(1)
 */
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
