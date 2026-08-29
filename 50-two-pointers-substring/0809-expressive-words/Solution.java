/**
 * LeetCode 809. Expressive Words
 * Approach: For each candidate word, two pointers walk `s` and the word
 * together comparing entire RUNS at a time rather than single
 * characters. Each matching run pair must share the same character; the
 * word's run can never be longer than s's run (that would mean s is
 * missing characters the word has); it can be shorter only if s's run is
 * at least 3 long (the "stretchy" rule -- fewer than 3 repeated
 * characters in s couldn't have been produced by extra key-holding).
 * Time: O(sum of word lengths) | Space: O(1) extra per word
 */
class Solution {
    public int expressiveWords(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isStretchy(s, word)) count++;
        }
        return count;
    }

    private boolean isStretchy(String s, String word) {
        int i = 0, j = 0;
        int n = s.length(), m = word.length();
        while (i < n && j < m) {
            if (s.charAt(i) != word.charAt(j)) return false;
            char c = s.charAt(i);

            int runS = 0;
            while (i < n && s.charAt(i) == c) {
                i++;
                runS++;
            }
            int runW = 0;
            while (j < m && word.charAt(j) == c) {
                j++;
                runW++;
            }

            if (runS < runW) return false;
            if (runS != runW && runS < 3) return false;
        }
        return i == n && j == m;
    }
}
