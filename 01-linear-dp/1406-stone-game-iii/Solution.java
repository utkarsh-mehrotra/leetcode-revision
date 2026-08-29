/**
 * LeetCode 1406. Stone Game III
 * Approach: Top-down memoized recursion -- bestDiff(i) is the best score
 * differential (mover minus opponent) achievable from stoneValue[i:],
 * trying takes of 1, 2, or 3 stones.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] stoneValue;
    private Integer[] memo;

    public String stoneGameIII(int[] stoneValue) {
        this.stoneValue = stoneValue;
        this.memo = new Integer[stoneValue.length + 1];
        int diff = bestDiff(0);
        if (diff > 0) return "Alice";
        if (diff < 0) return "Bob";
        return "Tie";
    }

    private int bestDiff(int i) {
        int n = stoneValue.length;
        if (i == n) return 0;
        if (memo[i] != null) return memo[i];
        int best = Integer.MIN_VALUE;
        int take = 0;
        for (int k = 0; k < 3 && i + k < n; k++) {
            take += stoneValue[i + k];
            best = Math.max(best, take - bestDiff(i + k + 1));
        }
        memo[i] = best;
        return best;
    }
}
