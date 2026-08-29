/**
 * LeetCode 920. Number of Music Playlists
 * Approach: Top-down memoized recursion over (songsPlaced, uniqueUsed) --
 * either place a brand-new song (n - uniqueUsed choices) or replay one
 * that's already been used and has fallen outside the last k slots
 * (uniqueUsed - k choices, only legal once uniqueUsed > k).
 * Time: O(goal * n) | Space: O(goal * n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int n, k;
    private Integer[][] dp;

    public int numMusicPlaylists(int n, int goal, int k) {
        this.n = n;
        this.k = k;
        this.dp = new Integer[goal + 1][n + 1];
        return ways(goal, n);
    }

    private int ways(int songsLeft, int uniqueLeft) {
        if (songsLeft == 0) return uniqueLeft == 0 ? 1 : 0;
        if (uniqueLeft <= 0) return 0;
        if (dp[songsLeft][uniqueLeft] != null) return dp[songsLeft][uniqueLeft];
        int uniqueUsed = n - uniqueLeft;
        long total = (long) ways(songsLeft - 1, uniqueLeft - 1) * uniqueLeft; // play a new song
        long replayChoices = Math.max(0, uniqueUsed - k);
        total = (total + replayChoices * ways(songsLeft - 1, uniqueLeft)) % MOD;
        dp[songsLeft][uniqueLeft] = (int) total;
        return (int) total;
    }
}
