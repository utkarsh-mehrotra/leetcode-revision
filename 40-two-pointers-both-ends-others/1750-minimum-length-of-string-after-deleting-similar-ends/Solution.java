/**
 * LeetCode 1750. Minimum Length of String After Deleting Similar Ends
 * Approach: Two pointers converging from both ends. While the characters
 * at both ends match, greedily strip the ENTIRE matching run from both
 * sides at once (not just one character) -- shrinking a run partially
 * can never be better, since any leftover of that same character would
 * just get stripped on the next step anyway. Stop when the ends differ
 * or the pointers meet/cross.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int minimumLength(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            char c = s.charAt(left);
            while (left <= right && s.charAt(left) == c) left++;
            while (right >= left && s.charAt(right) == c) right--;
        }
        return right - left + 1;
    }
}
