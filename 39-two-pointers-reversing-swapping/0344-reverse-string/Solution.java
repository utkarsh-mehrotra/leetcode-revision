/**
 * LeetCode 344. Reverse String
 * Approach: Two pointers converging from both ends, swapping in place.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}
