/**
 * LeetCode 517. Super Washing Machines
 * Approach: Top-down memoized recursion over the running "dress
 * balance" prefix (each machine's actual count minus the target
 * average) -- the minimum moves is the max, over every split point, of
 * how far out of balance that prefix is, since that many dresses must
 * cross that boundary; a single machine holding far more than average
 * also needs that many individual moves out of it.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] machines;
    private int avg;
    private Integer[] dp;

    public int findMinMoves(int[] machines) {
        int total = 0;
        for (int m : machines) total += m;
        int n = machines.length;
        if (total % n != 0) return -1;
        this.machines = machines;
        this.avg = total / n;
        this.dp = new Integer[n + 1];

        int best = 0;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, Math.abs(balancePrefix(i + 1)));
            best = Math.max(best, machines[i] - avg);
        }
        return best;
    }

    private int balancePrefix(int i) {
        if (i == 0) return 0;
        if (dp[i] != null) return dp[i];
        int result = balancePrefix(i - 1) + (machines[i - 1] - avg);
        dp[i] = result;
        return result;
    }
}
