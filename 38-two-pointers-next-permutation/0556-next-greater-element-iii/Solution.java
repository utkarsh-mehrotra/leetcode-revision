/**
 * LeetCode 556. Next Greater Element III
 * Approach: Run 31's next-permutation algorithm directly on n's decimal
 * digits: find the pivot from the right, swap it with the smallest
 * greater digit in the tail, then reverse the tail to make it ascending
 * (the minimal arrangement, so the smallest number greater than n with
 * these digits). If no pivot exists, the digits are already the largest
 * arrangement, so no greater permutation exists. The result is checked
 * against Integer.MAX_VALUE since it may overflow a 32-bit int.
 * Time: O(d) where d = digit count | Space: O(d)
 */
class Solution {
    public int nextGreaterElement(int n) {
        char[] digits = Integer.toString(n).toCharArray();
        int len = digits.length;

        int i = len - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) i--;
        if (i < 0) return -1;

        int j = len - 1;
        while (digits[j] <= digits[i]) j--;
        swap(digits, i, j);
        reverse(digits, i + 1, len - 1);

        long result = Long.parseLong(new String(digits));
        return result > Integer.MAX_VALUE ? -1 : (int) result;
    }

    private void reverse(char[] digits, int left, int right) {
        while (left < right) {
            swap(digits, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] digits, int i, int j) {
        char tmp = digits[i];
        digits[i] = digits[j];
        digits[j] = tmp;
    }
}
