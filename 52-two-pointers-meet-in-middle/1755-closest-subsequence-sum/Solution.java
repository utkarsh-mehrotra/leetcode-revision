import java.util.Arrays;

/**
 * LeetCode 1755. Closest Subsequence Sum
 * Approach: Meet in the middle. n up to 40 makes all 2^n subset sums too
 * many, but splitting into two halves of ~20 and enumerating each
 * half's subset sums separately (2^20 each) is feasible. Sort one
 * half's sums; then for every sum from the OTHER half, binary search
 * the sorted half for the value closest to (goal - thatSum) -- checking
 * both the insertion point and the entry just before it, since the true
 * closest total could round either way.
 * Time: O(2^(n/2) * (n/2)) | Space: O(2^(n/2))
 */
class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        int half1 = n / 2, half2 = n - half1;
        long[] sumsA = allSubsetSums(Arrays.copyOfRange(nums, 0, half1));
        long[] sumsB = allSubsetSums(Arrays.copyOfRange(nums, half1, n));
        Arrays.sort(sumsB);

        long best = Long.MAX_VALUE;
        for (long sumA : sumsA) {
            long target = goal - sumA;
            int idx = lowerBound(sumsB, target);
            if (idx < sumsB.length) {
                best = Math.min(best, Math.abs(sumA + sumsB[idx] - goal));
            }
            if (idx > 0) {
                best = Math.min(best, Math.abs(sumA + sumsB[idx - 1] - goal));
            }
        }
        return (int) best;
    }

    private long[] allSubsetSums(int[] arr) {
        int n = arr.length;
        long[] sums = new long[1 << n];
        for (int mask = 0; mask < (1 << n); mask++) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) sum += arr[i];
            }
            sums[mask] = sum;
        }
        return sums;
    }

    private int lowerBound(long[] sorted, long target) {
        int left = 0, right = sorted.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
