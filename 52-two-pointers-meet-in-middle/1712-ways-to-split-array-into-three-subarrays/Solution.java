/**
 * LeetCode 1712. Ways to Split Array Into Three Subarrays
 * Approach: Prefix sums are strictly increasing here (all values
 * positive), so for a fixed left boundary i, both the smallest valid
 * middle-boundary j (where sum(mid) >= sum(left)) and the largest valid
 * one (where sum(mid) <= sum(right)) move monotonically forward as i
 * increases -- a two-pointer sweep across all i finds every valid range
 * of j in O(n) total instead of re-searching per i.
 * Time: O(n) | Space: O(n) for prefix sums
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int waysToSplit(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) prefix[i] = prefix[i - 1] + nums[i];
        long total = prefix[n - 1];

        long count = 0;
        int low = 0, high = 0;
        for (int i = 0; i < n - 2; i++) {
            long sum1 = prefix[i];
            if (low < i + 1) low = i + 1;
            while (low < n - 1 && prefix[low] - sum1 < sum1) low++;

            if (high < low) high = low;
            while (high < n - 1 && (prefix[high] - sum1) <= (total - prefix[high])) high++;

            count = (count + Math.max(0, high - low)) % MOD;
        }
        return (int) count;
    }
}
