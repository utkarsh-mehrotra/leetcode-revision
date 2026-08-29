/**
 * LeetCode 376. Wiggle Subsequence
 * Approach: Top-down memoized recursion -- up(i)/down(i) is the longest
 * wiggle subsequence ending at index i whose last move was up/down, built
 * from the best down(j)/up(j) over every valid earlier index j.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[] upDp;
    private Integer[] downDp;

    public int wiggleMaxLength(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        upDp = new Integer[n];
        downDp = new Integer[n];
        int best = 1;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, Math.max(up(i), down(i)));
        }
        return best;
    }

    private int up(int i) {
        if (upDp[i] != null) return upDp[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                best = Math.max(best, down(j) + 1);
            }
        }
        upDp[i] = best;
        return best;
    }

    private int down(int i) {
        if (downDp[i] != null) return downDp[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (nums[i] < nums[j]) {
                best = Math.max(best, up(j) + 1);
            }
        }
        downDp[i] = best;
        return best;
    }
}
