/**
 * LeetCode 1671. Minimum Number of Removals to Make Mountain Array
 * Approach: Top-down memoized recursion computes, for every index, the
 * longest increasing run ending there (left to right) and the longest
 * increasing run starting there when scanned right to left (i.e. the
 * longest decreasing run to its right). Any index usable as a peak needs
 * both sides of length >= 2; the kept mountain length there is
 * leftLen(i) + rightLen(i) - 1, and removals are n minus the best such total.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private int n;
    private Integer[] leftDp;
    private Integer[] rightDp;

    public int minimumMountainRemovals(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.leftDp = new Integer[n];
        this.rightDp = new Integer[n];
        int bestKept = 0;
        for (int i = 0; i < n; i++) {
            int l = leftLen(i), r = rightLen(i);
            if (l >= 2 && r >= 2) {
                bestKept = Math.max(bestKept, l + r - 1);
            }
        }
        return n - bestKept;
    }

    private int leftLen(int i) {
        if (leftDp[i] != null) return leftDp[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) best = Math.max(best, leftLen(j) + 1);
        }
        leftDp[i] = best;
        return best;
    }

    private int rightLen(int i) {
        if (rightDp[i] != null) return rightDp[i];
        int best = 1;
        for (int j = i + 1; j < n; j++) {
            if (nums[j] < nums[i]) best = Math.max(best, rightLen(j) + 1);
        }
        rightDp[i] = best;
        return best;
    }
}
