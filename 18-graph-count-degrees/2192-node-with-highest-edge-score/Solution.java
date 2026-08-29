/**
 * LeetCode 2192. Node With Highest Edge Score
 * Approach: Each node's edge score is the sum of every node that points
 * to it, tallied with one pass over edges[]; the answer is the argmax
 * (smallest index on ties).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int edgeScore(int[] edges) {
        long[] score = new long[edges.length];
        for (int i = 0; i < edges.length; i++) {
            score[edges[i]] += i;
        }
        int best = 0;
        for (int i = 1; i < edges.length; i++) {
            if (score[i] > score[best]) best = i;
        }
        return best;
    }
}
