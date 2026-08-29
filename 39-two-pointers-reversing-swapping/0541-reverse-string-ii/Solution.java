/**
 * LeetCode 541. Reverse String II
 * Approach: Walk the string in blocks of 2k; within each block, reverse
 * only the first min(k, remaining) characters with a two-pointer swap
 * and leave the rest untouched.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int start = 0; start < n; start += 2 * k) {
            int left = start;
            int right = Math.min(start + k - 1, n - 1);
            while (left < right) {
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
