import java.util.Arrays;

/**
 * LeetCode 1040. Moving Stones Until Consecutive II
 * Approach: Sort the stones. The maximum moves is a direct formula: move
 * every stone except the two extremes toward one end, one at a time,
 * whichever end wastes fewer moves on the largest existing gap. The
 * minimum moves uses a sliding window (caterpillar) of width n (the
 * stone count) over the sorted positions -- the window covering the most
 * existing stones needs the fewest stones moved in from outside to fill
 * every remaining slot. One classic edge case: if the best window already
 * holds n-1 stones but leaves a gap of exactly 2 (not 1) at one end, the
 * lone outside stone can't slide directly into the single empty interior
 * slot in one move, so it costs 2 instead of the naive n - count = 1.
 * Time: O(n log n) | Space: O(1) extra
 */
class Solution {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;

        int maxMoves = Math.max(
                stones[n - 1] - stones[1] - (n - 2),
                stones[n - 2] - stones[0] - (n - 2)
        );

        int minMoves = n;
        int left = 0;
        for (int right = 0; right < n; right++) {
            while (stones[right] - stones[left] + 1 > n) left++;
            int count = right - left + 1;
            if (count == n - 1 && stones[right] - stones[left] == n - 2) {
                minMoves = Math.min(minMoves, 2);
            } else {
                minMoves = Math.min(minMoves, n - count);
            }
        }

        return new int[]{minMoves, maxMoves};
    }
}
