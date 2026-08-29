/**
 * LeetCode 1531. String Compression II
 * Approach: Top-down memoized recursion -- best(i, k) is the minimum
 * run-length-encoded length for s[i:] after deleting at most k of its
 * characters. Either delete s[i] outright, or keep it and extend a run of
 * up to k mismatches within the following window, paying the RLE cost
 * for that run once its length is fixed.
 * Time: O(n^2 * k) | Space: O(n * k)
 */
class Solution {
    private String s;
    private Integer[][] dp;

    public int getLengthOfOptimalCompression(String s, int k) {
        this.s = s;
        this.dp = new Integer[s.length() + 1][k + 1];
        return best(0, k);
    }

    private int best(int i, int k) {
        if (k < 0) return Integer.MAX_VALUE / 2;
        if (i + k >= s.length()) return 0; // the rest can be deleted away for free
        if (dp[i][k] != null) return dp[i][k];

        int result = best(i + 1, k - 1); // delete s[i] outright
        int count = 0, deletions = 0, maxFreq = 0;
        for (int j = i; j < s.length() && deletions <= k; j++) {
            if (s.charAt(j) == s.charAt(i)) {
                count++;
                maxFreq = Math.max(maxFreq, count);
            } else {
                deletions++;
            }
            if (deletions <= k) {
                result = Math.min(result, rleLength(maxFreq) + best(j + 1, k - deletions));
            }
        }
        dp[i][k] = result;
        return result;
    }

    private int rleLength(int runLength) {
        if (runLength == 1) return 1;
        if (runLength < 10) return 2;
        if (runLength < 100) return 3;
        return 4;
    }
}
