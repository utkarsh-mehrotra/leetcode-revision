/**
 * LeetCode 28. Find the Index of the First Occurrence in a String
 * (historically "Implement strStr()")
 * Approach: Try every starting position `i` in haystack as a candidate
 * match; a second pointer `j` walks needle in lockstep with `i+j`,
 * confirming the match character by character and bailing out at the
 * first mismatch. The outer loop only needs to try starts where enough
 * room remains for the whole needle to fit.
 * Time: O(n*m) worst case | Space: O(1)
 */
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) j++;
            if (j == m) return i;
        }
        return -1;
    }
}
