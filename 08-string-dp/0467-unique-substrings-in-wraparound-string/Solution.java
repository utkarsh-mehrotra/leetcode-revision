/**
 * LeetCode 467. Unique Substrings in Wraparound String
 * Approach: Top-down memoized recursion -- runLength(i) is the length of
 * the longest contiguous run ending at index i where each character is
 * exactly the wraparound-successor of the one before it. Every substring
 * ending at i and contained in that run is automatically a valid
 * wraparound substring, so the best run length per starting letter (there
 * are only 26) upper-bounds how many distinct such substrings end in that
 * letter; summing those maxima across letters avoids double-counting
 * duplicates.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private String p;
    private Integer[] dp;

    public int findSubstringInWraproundString(String p) {
        this.p = p;
        this.dp = new Integer[p.length()];
        int[] bestEndingIn = new int[26];
        for (int i = 0; i < p.length(); i++) {
            int c = p.charAt(i) - 'a';
            bestEndingIn[c] = Math.max(bestEndingIn[c], runLength(i));
        }
        int total = 0;
        for (int len : bestEndingIn) total += len;
        return total;
    }

    private int runLength(int i) {
        if (i == 0) return 1;
        if (dp[i] != null) return dp[i];
        boolean extends_ = (p.charAt(i) - p.charAt(i - 1) == 1) || (p.charAt(i - 1) == 'z' && p.charAt(i) == 'a');
        int result = extends_ ? runLength(i - 1) + 1 : 1;
        dp[i] = result;
        return result;
    }
}
