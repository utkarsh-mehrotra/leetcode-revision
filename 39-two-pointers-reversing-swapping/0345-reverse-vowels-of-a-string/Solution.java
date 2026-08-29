/**
 * LeetCode 345. Reverse Vowels of a String
 * Approach: Two pointers converging from both ends; advance either side
 * past consonants, and swap only when both land on vowels.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final String VOWELS = "aeiouAEIOU";

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            while (left < right && VOWELS.indexOf(chars[left]) == -1) left++;
            while (left < right && VOWELS.indexOf(chars[right]) == -1) right--;
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
