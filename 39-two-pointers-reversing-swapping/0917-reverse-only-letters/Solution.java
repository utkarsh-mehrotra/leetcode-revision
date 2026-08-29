/**
 * LeetCode 917. Reverse Only Letters
 * Approach: Two pointers converging from both ends; advance either side
 * past non-letters, and swap only when both land on letters.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            while (left < right && !Character.isLetter(chars[left])) left++;
            while (left < right && !Character.isLetter(chars[right])) right--;
            if (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }
}
