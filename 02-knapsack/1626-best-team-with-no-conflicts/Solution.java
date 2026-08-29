import java.util.Arrays;

/**
 * LeetCode 1626. Best Team With No Conflicts
 * Approach: Sort players by age (ties broken by score) so any valid team
 * is automatically non-decreasing in age; then this becomes an LIS-style
 * top-down memoized recursion -- teamEndingAt(i) is the best team sum
 * ending with player i, requiring every earlier teammate's score <= this
 * player's score.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[] sortedScores;
    private Integer[] dp;

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> ages[a] != ages[b] ? ages[a] - ages[b] : scores[a] - scores[b]);

        sortedScores = new int[n];
        for (int i = 0; i < n; i++) sortedScores[i] = scores[order[i]];

        dp = new Integer[n];
        int best = 0;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, teamEndingAt(i));
        }
        return best;
    }

    private int teamEndingAt(int i) {
        if (dp[i] != null) return dp[i];
        int best = sortedScores[i];
        for (int j = 0; j < i; j++) {
            if (sortedScores[j] <= sortedScores[i]) {
                best = Math.max(best, sortedScores[i] + teamEndingAt(j));
            }
        }
        dp[i] = best;
        return best;
    }
}
