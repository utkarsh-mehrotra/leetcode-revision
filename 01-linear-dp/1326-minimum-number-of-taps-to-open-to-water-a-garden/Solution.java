/**
 * LeetCode 1326. Minimum Number of Taps to Open to Water a Garden
 * Approach: Convert each tap into the farthest right position reachable
 * from every possible left-start position, reducing the problem to the
 * classic Jump Game II greedy: extend coverage step by step, incrementing
 * the tap count whenever the current reach is exhausted.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int minTaps(int[] ranges) {
        int n = ranges.length - 1;
        int[] farthestFrom = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            farthestFrom[left] = Math.max(farthestFrom[left], right);
        }

        int taps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i <= n; i++) {
            if (i > farthest) return -1;
            farthest = Math.max(farthest, farthestFrom[i]);
            if (i == currentEnd) {
                if (currentEnd == n) break;
                taps++;
                currentEnd = farthest;
            }
        }
        return currentEnd >= n ? taps : -1;
    }
}
