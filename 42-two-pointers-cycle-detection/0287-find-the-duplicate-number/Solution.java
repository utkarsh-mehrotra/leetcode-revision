/**
 * LeetCode 287. Find the Duplicate Number
 * Approach: Treat nums as a functional graph where index i points to
 * nums[i]. A duplicate value means two indices point to the same node,
 * which forces a cycle in this graph -- so Floyd's slow/fast pointer
 * algorithm finds a meeting point inside it, and the same reset-and-meet
 * walk used for a cyclic linked list's entry node finds the cycle's
 * entrance, which is exactly the duplicated value.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
