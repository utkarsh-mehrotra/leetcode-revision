/**
 * LeetCode 125. Valid Palindrome
 * Approach: Two pointers converging from both ends, skipping any
 * non-alphanumeric character on either side before comparing lowercase
 * values.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
