/**
 * LeetCode 1423. Maximum Points You Can Obtain From Cards
 * Approach: Taking k cards from the two ends is equivalent to leaving a
 * contiguous window of (n-k) cards in the middle untaken, so maximizing
 * the taken sum is the same as minimizing that middle window's sum.
 * Top-down memoized prefix-sum recursion makes every window sum O(1).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] cardPoints;
    private Integer[] dp;

    public int maxScore(int[] cardPoints, int k) {
        this.cardPoints = cardPoints;
        int n = cardPoints.length;
        this.dp = new Integer[n + 1];
        int total = prefixSum(n);

        int windowSize = n - k;
        if (windowSize == 0) return total;
        int minWindow = Integer.MAX_VALUE;
        for (int start = 0; start + windowSize <= n; start++) {
            int windowSum = prefixSum(start + windowSize) - prefixSum(start);
            minWindow = Math.min(minWindow, windowSum);
        }
        return total - minWindow;
    }

    private int prefixSum(int i) {
        if (i == 0) return 0;
        if (dp[i] != null) return dp[i];
        int result = prefixSum(i - 1) + cardPoints[i - 1];
        dp[i] = result;
        return result;
    }
}
