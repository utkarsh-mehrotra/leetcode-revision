/**
 * LeetCode 1186. Maximum Subarray Sum With One Deletion
 * Approach: Top-down memoized recursion -- noDelete(i)/withDelete(i) is
 * the max subarray sum ending at i using 0/1 deletions so far.
 * withDelete(i) either deletes arr[i] itself (keeping whatever streak
 * ended at i-1 with no deletion used) or keeps arr[i] and carries an
 * already-used deletion from before.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] arr;
    private Integer[] noDeleteDp;
    private Integer[] withDeleteDp;

    public int maximumSum(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        noDeleteDp = new Integer[n];
        withDeleteDp = new Integer[n];
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            best = Math.max(best, Math.max(noDelete(i), withDelete(i)));
        }
        return best;
    }

    private int noDelete(int i) {
        if (i == 0) return arr[0];
        if (noDeleteDp[i] != null) return noDeleteDp[i];
        int result = arr[i] + Math.max(0, noDelete(i - 1));
        noDeleteDp[i] = result;
        return result;
    }

    private int withDelete(int i) {
        if (i == 0) return Integer.MIN_VALUE / 2; // nothing before index 0 to delete
        if (withDeleteDp[i] != null) return withDeleteDp[i];
        int result = Math.max(noDelete(i - 1), arr[i] + withDelete(i - 1));
        withDeleteDp[i] = result;
        return result;
    }
}
