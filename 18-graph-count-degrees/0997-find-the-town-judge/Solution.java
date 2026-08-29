/**
 * LeetCode 997. Find the Town Judge
 * Approach: Track net trust score per person (indegree - outdegree). The
 * judge is trusted by everyone else (indegree n-1) and trusts no one
 * (outdegree 0), so their net score is exactly n-1 -- the only person who
 * can reach that score, since every trust edge contributes -1 to its
 * source and +1 to its target.
 * Time: O(n + trust.length) | Space: O(n)
 */
class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] score = new int[n + 1];
        for (int[] t : trust) {
            score[t[0]]--;
            score[t[1]]++;
        }
        for (int person = 1; person <= n; person++) {
            if (score[person] == n - 1) return person;
        }
        return -1;
    }
}
