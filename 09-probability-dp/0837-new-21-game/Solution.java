/**
 * LeetCode 837. New 21 Game
 * Approach: prob(i) is the probability of ever having an intermediate
 * total exactly i while still drawing (i < k); it's a sliding-window
 * average of the previous `maxPts` prob values, so a memoized prefix-sum
 * recursion (windowSumUpTo) makes each prob(i) O(1) after its window is
 * built. The final answer sums, over every achievable stopped total
 * s in [k, min(n, k+maxPts-1)], the probability of landing exactly on s
 * via one draw from a pre-k state -- the same window formula, just not
 * fed back into further recursion since s >= k stops drawing.
 * Time: O(k + maxPts) | Space: O(k)
 */
class Solution {
    private int k, w;
    private Double[] dp;
    private Double[] windowSumDp;

    public double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k + maxPts - 1) return 1.0;
        this.k = k;
        this.w = maxPts;
        dp = new Double[k];
        windowSumDp = new Double[k];
        double answer = 0;
        int upper = Math.min(n, k + maxPts - 1);
        for (int s = k; s <= upper; s++) {
            answer += finalProb(s);
        }
        return answer;
    }

    private double prob(int i) { // i in [0, k-1]: probability of passing through total i
        if (i == 0) return 1.0;
        if (dp[i] != null) return dp[i];
        double result = finalProb(i);
        dp[i] = result;
        return result;
    }

    private double windowSumUpTo(int i) { // sum of prob(0..i), for i in [-1, k-1]
        if (i < 0) return 0;
        if (windowSumDp[i] != null) return windowSumDp[i];
        double result = windowSumUpTo(i - 1) + prob(i);
        windowSumDp[i] = result;
        return result;
    }

    // Probability of landing exactly on total s via one draw from some pre-k state.
    private double finalProb(int s) {
        int hi = Math.min(s - 1, k - 1);
        int lo = Math.max(0, s - w);
        return (windowSumUpTo(hi) - windowSumUpTo(lo - 1)) / w;
    }
}
