/**
 * LeetCode 151. Reverse Words in a String
 * Approach: Classic in-place double-reverse. First, a read/write
 * two-pointer pass trims leading/trailing spaces and collapses interior
 * runs of spaces down to single spaces. Then reverse the whole cleaned
 * array with a two-pointer swap (this puts the words in the right order
 * but with each word's own letters backward), and finally reverse each
 * individual word in place to restore correct letter order within it.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        char[] cleaned = new char[n];
        int write = 0, read = 0;

        while (read < n && chars[read] == ' ') read++;
        while (read < n) {
            if (chars[read] != ' ') {
                cleaned[write++] = chars[read++];
            } else {
                while (read < n && chars[read] == ' ') read++;
                if (read < n) cleaned[write++] = ' ';
            }
        }
        if (write == 0) return "";

        reverse(cleaned, 0, write - 1);
        int start = 0;
        for (int i = 0; i <= write; i++) {
            if (i == write || cleaned[i] == ' ') {
                reverse(cleaned, start, i - 1);
                start = i + 1;
            }
        }
        return new String(cleaned, 0, write);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }
}
