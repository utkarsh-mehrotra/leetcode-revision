import java.util.Arrays;

/**
 * LeetCode 1478. Allocate Mailboxes
 * Approach: Sort houses, then interval DP -- best(i, boxesLeft) is the min
 * total distance serving houses[i:] with boxesLeft mailboxes. Try every
 * first group [i, j) served by one mailbox (optimally placed at the
 * group's median, an O(1)-per-house cost precomputed via groupCost).
 * Time: O(n^2 * k) | Space: O(n^2 + n * k)
 */
class Solution {
    private int[] houses;
    private int n;
    private Integer[][] dp;

    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        this.houses = houses;
        this.n = houses.length;
        this.dp = new Integer[n][k + 1];
        return best(0, k);
    }

    // Cost of covering houses[lo..hi) with one mailbox at the group's median.
    private int groupCost(int lo, int hi) {
        int cost = 0;
        int l = lo, r = hi - 1;
        while (l < r) {
            cost += houses[r] - houses[l];
            l++;
            r--;
        }
        return cost;
    }

    private int best(int i, int boxesLeft) {
        if (i == n) return 0;
        if (boxesLeft == 0) return Integer.MAX_VALUE / 2; // houses remain but no mailboxes left
        if (dp[i][boxesLeft] != null) return dp[i][boxesLeft];
        int result = Integer.MAX_VALUE / 2;
        // Leave enough houses for the remaining (boxesLeft - 1) mailboxes.
        for (int j = i + 1; j <= n - (boxesLeft - 1); j++) {
            int candidate = groupCost(i, j) + best(j, boxesLeft - 1);
            result = Math.min(result, candidate);
        }
        dp[i][boxesLeft] = result;
        return result;
    }
}
