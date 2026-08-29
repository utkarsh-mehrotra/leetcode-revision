import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 523. Continuous Subarray Sum
 * Approach: Not a memoized-recursion fit -- the key trick is that two
 * prefix sums sharing the same remainder mod k means the subarray between
 * them sums to a multiple of k. A single pass with a hashmap of each
 * remainder's FIRST index finds the longest (hence best) such gap in O(1)
 * per step; no overlapping subproblem to cache.
 * Time: O(n) | Space: O(min(n, k))
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> firstIndexOfRemainder = new HashMap<>();
        firstIndexOfRemainder.put(0, -1);
        int prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            int remainder = ((prefix % k) + k) % k;
            Integer firstIndex = firstIndexOfRemainder.get(remainder);
            if (firstIndex != null) {
                if (i - firstIndex >= 2) return true;
            } else {
                firstIndexOfRemainder.put(remainder, i);
            }
        }
        return false;
    }
}
