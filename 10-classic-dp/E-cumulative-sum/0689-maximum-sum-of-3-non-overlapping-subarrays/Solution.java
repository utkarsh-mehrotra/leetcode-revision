/**
 * LeetCode 689. Maximum Sum of 3 Non-Overlapping Subarrays
 * Approach: Top-down memoized recursion over (start, windowsLeft) -- the
 * best achievable sum choosing windowsLeft more non-overlapping length-k
 * windows from index start onward, either skipping index start or taking
 * the window beginning there and jumping k ahead. Window sums are O(1)
 * via a memoized prefix sum. The actual starting indices are recovered by
 * replaying the same greedy comparison used to build the DP.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private int k;
    private int n;
    private Integer[] prefixDp;
    private Integer[][] bestDp;

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        this.n = nums.length;
        this.prefixDp = new Integer[n + 1];
        this.bestDp = new Integer[n + 1][4];

        int[] result = new int[3];
        int start = 0, windowsLeft = 3, resultIdx = 0;
        while (windowsLeft > 0) {
            int takeValue = (start + k <= n) ? windowSum(start) + best(start + k, windowsLeft - 1) : Integer.MIN_VALUE;
            int skipValue = best(start + 1, windowsLeft);
            if (takeValue >= skipValue) {
                result[resultIdx++] = start;
                start += k;
                windowsLeft--;
            } else {
                start += 1;
            }
        }
        return result;
    }

    private int best(int start, int windowsLeft) {
        if (windowsLeft == 0) return 0;
        if (start + k * windowsLeft > n) return Integer.MIN_VALUE;
        if (bestDp[start][windowsLeft] != null) return bestDp[start][windowsLeft];
        int take = windowSum(start) + best(start + k, windowsLeft - 1);
        int skip = best(start + 1, windowsLeft);
        int result = Math.max(take, skip);
        bestDp[start][windowsLeft] = result;
        return result;
    }

    private int windowSum(int start) {
        return prefixSum(start + k) - prefixSum(start);
    }

    private int prefixSum(int i) {
        if (i == 0) return 0;
        if (prefixDp[i] != null) return prefixDp[i];
        int result = prefixSum(i - 1) + nums[i - 1];
        prefixDp[i] = result;
        return result;
    }
}
