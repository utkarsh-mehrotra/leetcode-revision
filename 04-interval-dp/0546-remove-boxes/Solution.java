/**
 * LeetCode 546. Remove Boxes
 * Approach: Interval DP with an extra "attached" count -- best(lo, hi, k)
 * is the max points removing boxes[lo..hi], given that k extra boxes of
 * boxes[hi]'s color are already grouped with it (from boxes removed
 * earlier that were the same color). Either burst boxes[hi] together with
 * its k attachments now, or attach it to some earlier same-color box[i]
 * first (removing whatever sits between them independently).
 * Time: O(n^4) | Space: O(n^3)
 */
class Solution {
    private int[] boxes;
    private Integer[][][] dp;

    public int removeBoxes(int[] boxes) {
        this.boxes = boxes;
        int n = boxes.length;
        this.dp = new Integer[n][n][n];
        return best(0, n - 1, 0);
    }

    private int best(int lo, int hi, int k) {
        if (lo > hi) return 0;
        if (dp[lo][hi][k] != null) return dp[lo][hi][k];

        // Shrink the trailing run of boxes[hi]'s color into the attached count up front.
        int origHi = hi, origK = k;
        while (hi > lo && boxes[hi] == boxes[hi - 1]) {
            hi--;
            k++;
        }

        int result = (k + 1) * (k + 1) + best(lo, hi - 1, 0);
        for (int i = lo; i < hi; i++) {
            if (boxes[i] == boxes[hi]) {
                result = Math.max(result, best(lo, i, k + 1) + best(i + 1, hi - 1, 0));
            }
        }
        dp[lo][origHi][origK] = result;
        return result;
    }
}
