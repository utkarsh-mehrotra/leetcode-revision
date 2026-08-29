/**
 * LeetCode 376. Wiggle Subsequence
 * Approach: Top-down memoized recursion -- up(i)/down(i) is the longest
 * wiggle subsequence ending at index i whose last move was up/down, built
 * from the best down(j)/up(j) over every valid earlier index j.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[] upMemo;
    private Integer[] downMemo;

    public int wiggleMaxLength(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        upMemo = new Integer[n];
        downMemo = new Integer[n];
        int best = 1;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, Math.max(up(i), down(i)));
        }
        return best;
    }

    private int up(int i) {
        if (upMemo[i] != null) return upMemo[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                best = Math.max(best, down(j) + 1);
            }
        }
        upMemo[i] = best;
        return best;
    }

    private int down(int i) {
        if (downMemo[i] != null) return downMemo[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (nums[i] < nums[j]) {
                best = Math.max(best, up(j) + 1);
            }
        }
        downMemo[i] = best;
        return best;
    }
}
