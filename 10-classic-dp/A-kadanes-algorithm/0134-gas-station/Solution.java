/**
 * LeetCode 134. Gas Station
 * Approach: Kadane-adjacent single pass rather than a memoized recursion
 * -- if a solution exists it's unique, and it starts right after the
 * point where the running (gas - cost) balance hits its global minimum
 * (everywhere before that point is "in debt" relative to the rest of the
 * loop, so starting there is never better). No overlapping subproblem to
 * cache; each station is examined once.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalBalance = 0;
        int runningBalance = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            totalBalance += diff;
            runningBalance += diff;
            if (runningBalance < 0) {
                start = i + 1;
                runningBalance = 0;
            }
        }
        return totalBalance >= 0 ? start : -1;
    }
}
