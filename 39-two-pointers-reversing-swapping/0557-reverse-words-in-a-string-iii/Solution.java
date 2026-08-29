/**
 * LeetCode 557. Reverse Words in a String III
 * Approach: Word order and spacing stay untouched here (unlike 151) --
 * just reverse each space-delimited word in place with a two-pointer
 * swap between its start and end boundaries.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int start = 0;
        for (int i = 0; i <= n; i++) {
            if (i == n || chars[i] == ' ') {
                reverse(chars, start, i - 1);
                start = i + 1;
            }
        }
        return new String(chars);
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
