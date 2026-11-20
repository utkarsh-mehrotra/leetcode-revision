/**
 * LeetCode 521. Longest Uncommon Subsequence II
 * Approach: A word qualifies iff it is NOT a subsequence of any OTHER
 * word in the list (a word is trivially its own subsequence, so
 * self-comparisons are skipped) -- checked with the same two-pointer
 * subsequence scan as 524. Among all qualifying words, the longest wins;
 * ties are impossible to matter here since any duplicate automatically
 * disqualifies both copies (each is a subsequence of the other).
 * Time: O(n^2 * L) | Space: O(1) extra
 */
class Solution {
    public int findLUSlength(String[] strs) {
        int best = -1;
        for (int i = 0; i < strs.length; i++) {
            if (isUncommon(strs, i)) {
                best = Math.max(best, strs[i].length());
            }
        }
        return best;
    }

    private boolean isUncommon(String[] strs, int idx) {
        for (int j = 0; j < strs.length; j++) {
            if (j != idx && isSubsequence(strs[idx], strs[j])) return false;
        }
        return true;
    }

    private boolean isSubsequence(String word, String other) {
        int i = 0, j = 0;
        while (i < word.length() && j < other.length()) {
            if (word.charAt(i) == other.charAt(j)) i++;
            j++;
        }
        return i == word.length();
    }
}
