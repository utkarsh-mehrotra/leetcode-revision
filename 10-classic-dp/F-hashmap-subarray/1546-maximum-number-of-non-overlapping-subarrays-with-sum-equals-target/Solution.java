import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
 * Approach: Greedy, not a DP recursion -- whenever the running prefix sum
 * minus target has been seen before, a valid subarray ends here; taking
 * it immediately and resetting (clearing the seen-prefix set and prefix
 * back to a fresh start) is provably never worse than waiting for a
 * later end point, since it only frees up more room for future subarrays.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        Set<Integer> seenPrefixes = new HashSet<>();
        seenPrefixes.add(0);
        int prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            if (seenPrefixes.contains(prefix - target)) {
                count++;
                seenPrefixes.clear();
                seenPrefixes.add(0);
                prefix = 0;
            } else {
                seenPrefixes.add(prefix);
            }
        }
        return count;
    }
}
