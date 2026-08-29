/**
 * LeetCode 680. Valid Palindrome II
 * Approach: Two pointers converging from both ends. On the first
 * mismatch, at most one deletion is allowed, so try skipping either the
 * left or the right character and check whether the remaining substring
 * is a plain palindrome (also via converging two pointers) -- if either
 * skip works, the original is a valid "almost palindrome".
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
