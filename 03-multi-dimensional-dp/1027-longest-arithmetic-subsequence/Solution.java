import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1027. Longest Arithmetic Subsequence
 * Approach: Top-down memoized recursion by index -- solve(i) fills
 * dp[i], a map from common-difference to the longest arithmetic
 * subsequence ending at i with that difference, using every earlier
 * index j (whose own map is guaranteed ready first, since solve(i)
 * recurses to solve(i-1) before computing its own map).
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] arr;
    private int n;
    private Map<Integer, Integer>[] dp;
    private Integer[] bestEndingAt;

    @SuppressWarnings("unchecked")
    public int longestArithSeqLength(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.dp = new HashMap[n];
        this.bestEndingAt = new Integer[n];
        int best = 1;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, solve(i));
        }
        return best;
    }

    private int solve(int i) {
        if (bestEndingAt[i] != null) return bestEndingAt[i];
        if (i > 0) solve(i - 1); // ensure every earlier index's map is already built
        dp[i] = new HashMap<>();
        int best = 1;
        for (int j = 0; j < i; j++) {
            int diff = arr[i] - arr[j];
            int length = dp[j].getOrDefault(diff, 1) + 1;
            dp[i].merge(diff, length, Math::max);
            best = Math.max(best, length);
        }
        bestEndingAt[i] = best;
        return best;
    }
}
