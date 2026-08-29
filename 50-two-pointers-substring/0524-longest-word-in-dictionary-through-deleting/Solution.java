/**
 * LeetCode 524. Longest Word in Dictionary through Deleting
 * Approach: For each dictionary word, a two-pointer subsequence check --
 * one pointer over s, one over the candidate word -- confirms whether
 * the word can be formed by deleting characters from s (advance the s
 * pointer always, advance the word pointer only on a match; the word is
 * a valid subsequence iff its pointer reaches the end). Among all valid
 * words, keep the longest, breaking ties lexicographically smallest.
 * Time: O(sum of word lengths * |s|) | Space: O(1) extra
 */
class Solution {
    public String findLongestWord(String s, java.util.List<String> dictionary) {
        String best = "";
        for (String word : dictionary) {
            if (isSubsequence(word, s) && isBetter(word, best)) {
                best = word;
            }
        }
        return best;
    }

    private boolean isSubsequence(String word, String s) {
        int i = 0, j = 0;
        while (i < word.length() && j < s.length()) {
            if (word.charAt(i) == s.charAt(j)) i++;
            j++;
        }
        return i == word.length();
    }

    private boolean isBetter(String candidate, String current) {
        if (candidate.length() != current.length()) return candidate.length() > current.length();
        return candidate.compareTo(current) < 0;
    }
}
