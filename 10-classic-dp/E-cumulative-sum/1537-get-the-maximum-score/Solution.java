/**
 * LeetCode 1537. Get the Maximum Score
 * Approach: Not a DP recursion -- merge-walk both sorted arrays with two
 * pointers, accumulating each array's running sum since the last value
 * they had in common. The path may only switch arrays at a shared value,
 * so at each meeting point take whichever running sum is larger plus the
 * shared value, then reset both sums. No overlapping subproblem to cache.
 * Time: O(m + n) | Space: O(1) extra
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int maxSum(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        long sum1 = 0, sum2 = 0, result = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                sum1 += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                sum2 += nums2[j++];
            } else {
                result += Math.max(sum1, sum2) + nums1[i];
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
        }
        while (i < nums1.length) sum1 += nums1[i++];
        while (j < nums2.length) sum2 += nums2[j++];
        result += Math.max(sum1, sum2);
        return (int) (result % MOD);
    }
}
