/**
 * LeetCode 925. Long Pressed Name
 * Approach: Two pointers walking `name` and `typed` together. Matching
 * characters advance both; a mismatch is only tolerable if `typed` is
 * repeating the PREVIOUS character of `name` (a long-press), in which
 * case only `typed` advances. Any other mismatch, or `name` finishing
 * while `typed` still has unexplained extra characters, means it's not
 * a valid long-press of `name`.
 * Time: O(n+m) | Space: O(1)
 */
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        int n = name.length(), m = typed.length();
        while (j < m) {
            if (i < n && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == n;
    }
}
