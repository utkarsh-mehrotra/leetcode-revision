/**
 * LeetCode 1850. Minimum Adjacent Swaps to Reach the Kth Smallest Number
 * (Note: this problem is mislabeled "1830" in some problem lists; its
 * real LeetCode number is 1850.)
 * Approach: Two phases, both built on 31's next-permutation algorithm.
 * First, apply next-permutation to num's digits k times to find the
 * target arrangement (constraints keep num.length and k small enough
 * that this direct repetition is fast). Second, greedily transform num's
 * own digits into that target: for each position left to right, scan
 * rightward for the next unconsumed digit matching the target and bubble
 * it into place with adjacent swaps, counting them as we go -- the
 * standard minimum-adjacent-swaps-between-two-arrangements technique,
 * which stays correct even with repeated digits since always taking the
 * LEFTMOST matching occurrence never wastes a swap.
 * Time: O(k*n + n²) | Space: O(n)
 */
class Solution {
    public int getMinSwaps(String num, int k) {
        char[] target = num.toCharArray();
        for (int i = 0; i < k; i++) {
            nextPermutation(target);
        }

        char[] cur = num.toCharArray();
        int swaps = 0;
        int n = cur.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (cur[j] != target[i]) j++;
            while (j > i) {
                char tmp = cur[j];
                cur[j] = cur[j - 1];
                cur[j - 1] = tmp;
                j--;
                swaps++;
            }
        }
        return swaps;
    }

    private void nextPermutation(char[] digits) {
        int n = digits.length;
        int i = n - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) i--;

        if (i >= 0) {
            int j = n - 1;
            while (digits[j] <= digits[i]) j--;
            char tmp = digits[i];
            digits[i] = digits[j];
            digits[j] = tmp;
        }

        int left = i + 1, right = n - 1;
        while (left < right) {
            char tmp = digits[left];
            digits[left] = digits[right];
            digits[right] = tmp;
            left++;
            right--;
        }
    }
}
