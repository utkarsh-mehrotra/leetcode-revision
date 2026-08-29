/**
 * LeetCode 978. Longest Turbulent Subarray
 * Approach: Top-down memoized recursion -- up(i)/down(i) is the longest
 * turbulent run ending at i whose last comparison was an increase/decrease,
 * built from the opposite-direction run ending at i-1 (turbulence
 * requires strict alternation between consecutive elements only, unlike
 * general wiggle subsequences, so this only ever looks one index back).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] arr;
    private Integer[] upDp;
    private Integer[] downDp;

    public int maxTurbulenceSize(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        upDp = new Integer[n];
        downDp = new Integer[n];
        int best = 1;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, Math.max(up(i), down(i)));
        }
        return best;
    }

    private int up(int i) {
        if (i == 0) return 1;
        if (upDp[i] != null) return upDp[i];
        int result = (arr[i] > arr[i - 1]) ? down(i - 1) + 1 : 1;
        upDp[i] = result;
        return result;
    }

    private int down(int i) {
        if (i == 0) return 1;
        if (downDp[i] != null) return downDp[i];
        int result = (arr[i] < arr[i - 1]) ? up(i - 1) + 1 : 1;
        downDp[i] = result;
        return result;
    }
}
