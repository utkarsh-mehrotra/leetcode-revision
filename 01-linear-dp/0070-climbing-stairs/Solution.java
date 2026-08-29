/**
 * LeetCode 70. Climbing Stairs
 * Approach: Fibonacci recurrence -- ways(n) = ways(n-1) + ways(n-2), computed
 * bottom-up with two rolling variables instead of an array.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
