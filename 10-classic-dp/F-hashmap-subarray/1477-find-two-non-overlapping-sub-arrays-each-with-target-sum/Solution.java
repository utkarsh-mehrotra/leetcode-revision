import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1477. Find Two Non-Overlapping Sub-arrays Each With Target Sum
 * Approach: Since every element is positive, prefix sums are strictly
 * increasing, so each prefix-sum value maps to a unique index -- a single
 * pass hashmap lookup finds, for each i, whether a target-sum subarray
 * ends exactly there. minLenUpTo(i) (the shortest such subarray ending at
 * or before i) is threaded through the same pass, so combining the
 * current subarray with the best non-overlapping earlier one is O(1).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        Map<Integer, Integer> indexOfPrefix = new HashMap<>();
        indexOfPrefix.put(0, -1);
        int n = arr.length;
        int[] minLenUpTo = new int[n];
        int prefix = 0;
        int best = Integer.MAX_VALUE;
        int runningBest = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            Integer j = indexOfPrefix.get(prefix - target);
            if (j != null) {
                int length = i - j;
                int earlierBest = (j == -1) ? Integer.MAX_VALUE : minLenUpTo[j];
                if (earlierBest != Integer.MAX_VALUE) {
                    best = Math.min(best, length + earlierBest);
                }
                runningBest = Math.min(runningBest, length);
            }
            minLenUpTo[i] = runningBest;
            indexOfPrefix.put(prefix, i);
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}
