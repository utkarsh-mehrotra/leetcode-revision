/**
 * LeetCode 832. Flipping an Image
 * Approach: Per row, two pointers converging from both ends: swap the
 * pair (horizontal flip) and XOR each with 1 (invert) at the same time,
 * so both operations happen in a single in-place pass. A row of odd
 * length leaves a middle element that only needs the invert.
 * Time: O(rows*cols) | Space: O(1) extra
 */
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        for (int[] row : image) {
            int left = 0, right = row.length - 1;
            while (left < right) {
                int tmp = row[left] ^ 1;
                row[left] = row[right] ^ 1;
                row[right] = tmp;
                left++;
                right--;
            }
            if (left == right) row[left] ^= 1;
        }
        return image;
    }
}
