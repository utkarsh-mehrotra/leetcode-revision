/**
 * LeetCode 1754. Largest Merge of Two Strings
 * Approach: Two pointers walking word1 and word2 from the start, merging
 * greedily -- but "greedy" here means comparing the entire REMAINING
 * suffix of each word (not just the current character), since a tie on
 * the current character can only be broken by what follows. Whichever
 * word's remaining suffix is lexicographically larger contributes its
 * next character to the result.
 * Time: O((n+m)²) worst case (suffix comparisons) | Space: O(n+m)
 */
class Solution {
    public String largestMerge(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;
        while (i < word1.length() && j < word2.length()) {
            if (word1.substring(i).compareTo(word2.substring(j)) > 0) {
                result.append(word1.charAt(i++));
            } else {
                result.append(word2.charAt(j++));
            }
        }
        result.append(word1.substring(i));
        result.append(word2.substring(j));
        return result.toString();
    }
}
