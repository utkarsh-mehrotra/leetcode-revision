/**
 * LeetCode 2000. Reverse Prefix of Word
 * Approach: Find the first occurrence of `ch`, then reverse the prefix
 * up to and including it with a two-pointer swap.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public String reversePrefix(String word, char ch) {
        int idx = word.indexOf(ch);
        if (idx == -1) return word;

        char[] chars = word.toCharArray();
        int left = 0, right = idx;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
        return new String(chars);
    }
}
