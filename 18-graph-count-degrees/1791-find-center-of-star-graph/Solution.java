/**
 * LeetCode 1791. Find Center of Star Graph
 * Approach: In a star graph every edge touches the center, so it must
 * appear in both of the first two edges -- whichever endpoint they share
 * is the center.
 * Time: O(1) | Space: O(1)
 */
class Solution {
    public int findCenter(int[][] edges) {
        int a1 = edges[0][0], a2 = edges[0][1];
        int b1 = edges[1][0], b2 = edges[1][1];
        return (a1 == b1 || a1 == b2) ? a1 : a2;
    }
}
