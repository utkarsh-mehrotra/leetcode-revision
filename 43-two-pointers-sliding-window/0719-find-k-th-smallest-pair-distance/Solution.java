import java.util.Arrays;

/**
 * LeetCode 719. Find K-th Smallest Pair Distance
 * Approach: Binary search on the answer distance; for each candidate
 * `mid`, count how many pairs have distance <= mid using a sliding
 * window (caterpillar) over the sorted array -- for each right endpoint,
 * shrink the left edge until the window's span fits within mid, and
 * every index in between forms a valid pair with `right`. The smallest
 * mid whose count reaches k is the k-th smallest distance.
 * Time: O(n log n + n log(maxDist)) | Space: O(1) extra
 */
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int lo = 0, hi = nums[n - 1] - nums[0];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (countPairsWithinDistance(nums, mid) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int countPairsWithinDistance(int[] nums, int maxDist) {
        int left = 0, count = 0;
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > maxDist) left++;
            count += right - left;
        }
        return count;
    }
}
