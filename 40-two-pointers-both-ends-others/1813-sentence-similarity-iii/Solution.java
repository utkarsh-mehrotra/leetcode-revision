/**
 * LeetCode 1813. Sentence Similarity III
 * Approach: Split both sentences into word arrays and let the shorter one
 * be w1. Two independent two-pointer scans converge from both ends: one
 * counts how many words match as a common PREFIX (front pointers), the
 * other how many match as a common SUFFIX (back pointers, capped so it
 * never re-uses a word already claimed by the prefix match). If the
 * prefix and suffix matches together cover every word of the shorter
 * sentence, the extra words in the longer one form exactly one
 * contiguous block that could have been inserted in the middle.
 * Time: O(n) | Space: O(n) for the split word arrays
 */
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] w1 = sentence1.split(" ");
        String[] w2 = sentence2.split(" ");
        if (w1.length > w2.length) {
            String[] tmp = w1;
            w1 = w2;
            w2 = tmp;
        }
        int n1 = w1.length, n2 = w2.length;

        int prefixMatch = 0;
        while (prefixMatch < n1 && w1[prefixMatch].equals(w2[prefixMatch])) {
            prefixMatch++;
        }

        int suffixMatch = 0;
        while (suffixMatch < n1 - prefixMatch
                && w1[n1 - 1 - suffixMatch].equals(w2[n2 - 1 - suffixMatch])) {
            suffixMatch++;
        }

        return prefixMatch + suffixMatch >= n1;
    }
}
