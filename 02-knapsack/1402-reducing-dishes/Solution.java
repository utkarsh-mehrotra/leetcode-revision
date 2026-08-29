import java.util.Arrays;

/**
 * LeetCode 1402. Reducing Dishes
 * Approach: Sort satisfaction ascending -- the optimal cooking order is
 * always a contiguous suffix of the sorted array. Top-down memoized
 * recursion: total(i) = suffixSum(i) + total(i+1), since extending the
 * cooked suffix left by one dish shifts every later multiplier up by 1
 * (adding suffixSum(i+1)) while the new dish itself contributes at
 * multiplier 1 (folded into suffixSum(i)).
 * Time: O(n log n) | Space: O(n)
 */
class Solution {
    private int[] satisfaction;
    private Long[] suffixSumDp;
    private Long[] totalDp;

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        this.satisfaction = satisfaction;
        int n = satisfaction.length;
        suffixSumDp = new Long[n + 1];
        totalDp = new Long[n + 1];
        long best = 0; // cooking nothing is always a valid, zero-satisfaction option
        for (int i = 0; i <= n; i++) {
            best = Math.max(best, total(i));
        }
        return (int) best;
    }

    private long suffixSum(int i) {
        if (i == satisfaction.length) return 0;
        if (suffixSumDp[i] != null) return suffixSumDp[i];
        long result = satisfaction[i] + suffixSum(i + 1);
        suffixSumDp[i] = result;
        return result;
    }

    private long total(int i) {
        if (i == satisfaction.length) return 0;
        if (totalDp[i] != null) return totalDp[i];
        long result = suffixSum(i) + total(i + 1);
        totalDp[i] = result;
        return result;
    }
}
