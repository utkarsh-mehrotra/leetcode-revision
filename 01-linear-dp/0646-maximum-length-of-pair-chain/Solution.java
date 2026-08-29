import java.util.Arrays;

/**
 * LeetCode 646. Maximum Length of Pair Chain
 * Approach: Classic activity-selection greedy -- sort pairs by their second
 * element and always extend the chain with the earliest-finishing pair
 * whose start exceeds the current chain's end. Strictly dominates the
 * O(n^2) DP formulation of this problem.
 * Time: O(n log n) | Space: O(1) extra
 */
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 0;
        int currentEnd = Integer.MIN_VALUE;
        for (int[] pair : pairs) {
            if (pair[0] > currentEnd) {
                count++;
                currentEnd = pair[1];
            }
        }
        return count;
    }
}
