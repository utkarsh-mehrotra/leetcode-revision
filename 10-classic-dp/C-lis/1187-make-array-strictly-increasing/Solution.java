import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1187. Make Array Strictly Increasing
 * Approach: Top-down memoized recursion over (i, prevValue) -- solve(i,
 * prev) is the min replacements to make arr1[i:] strictly increasing
 * given the previous kept/replaced value is prev. Either keep arr1[i]
 * (only if it beats prev), or replace it with the smallest arr2 value
 * greater than prev (found by binary search on sorted, deduplicated
 * arr2). prev only ever takes values from arr1 or sorted-arr2, so it's
 * mapped to a compact index for memoization.
 * Time: O(n * (n+m) * log m) | Space: O(n * (n+m))
 */
class Solution {
    private int[] arr1;
    private int[] sortedArr2;
    private int n;
    private Integer[][] dp; // dp[i][prevValueIndex]
    private Map<Integer, Integer> valueToIndex;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.n = arr1.length;
        int[] dedupArr2 = Arrays.stream(arr2).distinct().sorted().toArray();
        this.sortedArr2 = dedupArr2;

        // Compact index space for every value that could ever appear as "prev".
        java.util.TreeSet<Integer> allValues = new java.util.TreeSet<>();
        allValues.add(Integer.MIN_VALUE);
        for (int v : arr1) allValues.add(v);
        for (int v : dedupArr2) allValues.add(v);
        valueToIndex = new HashMap<>();
        int idx = 0;
        for (int v : allValues) valueToIndex.put(v, idx++);

        dp = new Integer[n][allValues.size()];
        int result = solve(0, Integer.MIN_VALUE);
        return result >= INF ? -1 : result;
    }

    private int solve(int i, int prev) {
        if (i == n) return 0;
        int prevIdx = valueToIndex.get(prev);
        if (dp[i][prevIdx] != null) return dp[i][prevIdx];

        int best = INF;
        if (arr1[i] > prev) {
            best = Math.min(best, solve(i + 1, arr1[i]));
        }
        int replacement = smallestGreaterThan(prev);
        if (replacement != Integer.MAX_VALUE) {
            best = Math.min(best, 1 + solve(i + 1, replacement));
        }
        dp[i][prevIdx] = best;
        return best;
    }

    private int smallestGreaterThan(int value) {
        int lo = 0, hi = sortedArr2.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (sortedArr2[mid] <= value) lo = mid + 1;
            else hi = mid;
        }
        return lo == sortedArr2.length ? Integer.MAX_VALUE : sortedArr2[lo];
    }
}
