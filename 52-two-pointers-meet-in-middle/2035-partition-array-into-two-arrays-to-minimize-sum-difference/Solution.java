import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 2035. Partition Array Into Two Arrays To Minimize Sum Difference
 * Approach: Meet in the middle. Splitting nums (length 2n) into two
 * groups of exactly n each means the answer is |2*sum(group1) - total|
 * minimized, i.e. finding a size-n subset whose sum is as close to
 * total/2 as possible. Split nums into two computational halves; for
 * each half, enumerate every subset's sum GROUPED BY how many elements
 * it uses (feasible since each half has ~n/2 elements, so 2^(n/2)
 * subsets total, spread across n/2+1 size buckets). For every count k,
 * pairing a size-k subset from half A with a size-(n-k) subset from half
 * B gives a valid size-n group; binary search half B's sorted bucket for
 * the sum closest to (total/2 - sumA) to minimize the final difference.
 * Time: O(2^(n/2) * n) | Space: O(2^(n/2))
 */
class Solution {
    public int minimumDifference(int[] nums) {
        int total2n = nums.length;
        int n = total2n / 2;
        int[] a = Arrays.copyOfRange(nums, 0, n);
        int[] b = Arrays.copyOfRange(nums, n, total2n);

        long totalSum = 0;
        for (int v : nums) totalSum += v;

        List<List<Long>> sumsA = subsetSumsBySize(a);
        List<List<Long>> sumsB = subsetSumsBySize(b);

        long best = Long.MAX_VALUE;
        for (int k = 0; k <= n; k++) {
            List<Long> bucketB = sumsB.get(n - k);
            for (long sumA : sumsA.get(k)) {
                double target = totalSum / 2.0 - sumA;
                int idx = lowerBound(bucketB, target);
                if (idx < bucketB.size()) {
                    best = Math.min(best, Math.abs(totalSum - 2 * (sumA + bucketB.get(idx))));
                }
                if (idx > 0) {
                    best = Math.min(best, Math.abs(totalSum - 2 * (sumA + bucketB.get(idx - 1))));
                }
            }
        }
        return (int) best;
    }

    // Groups every subset sum of arr by subset size, each bucket sorted.
    private List<List<Long>> subsetSumsBySize(int[] arr) {
        int n = arr.length;
        List<List<Long>> buckets = new ArrayList<>();
        for (int i = 0; i <= n; i++) buckets.add(new ArrayList<>());

        for (int mask = 0; mask < (1 << n); mask++) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) sum += arr[i];
            }
            buckets.get(Integer.bitCount(mask)).add(sum);
        }
        for (List<Long> bucket : buckets) Collections.sort(bucket);
        return buckets;
    }

    private int lowerBound(List<Long> sorted, double target) {
        int left = 0, right = sorted.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sorted.get(mid) < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
