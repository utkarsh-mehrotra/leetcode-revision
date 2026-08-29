/**
 * LeetCode 132. Palindrome Partitioning II
 * Approach: Top-down memoized recursion -- minCuts(i) is the fewest cuts
 * needed to make every piece of s[i:] a palindrome. If the whole suffix
 * is already a palindrome, 0 cuts suffice; otherwise try every first-piece
 * end point that's a palindrome and take 1 + the best for the remainder.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private String s;
    private int n;
    private Boolean[][] palDp;
    private Integer[] cutsDp;

    public int minCut(String s) {
        this.s = s;
        this.n = s.length();
        this.palDp = new Boolean[n][n];
        this.cutsDp = new Integer[n + 1];
        return minCuts(0);
    }

    private int minCuts(int i) {
        if (i == n) return -1; // sentinel so the caller's "+1" yields 0 cuts for an empty suffix
        if (isPalindrome(i, n - 1)) return 0;
        if (cutsDp[i] != null) return cutsDp[i];
        int best = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j)) {
                best = Math.min(best, 1 + minCuts(j + 1));
            }
        }
        cutsDp[i] = best;
        return best;
    }

    private boolean isPalindrome(int lo, int hi) {
        if (lo >= hi) return true;
        if (palDp[lo][hi] != null) return palDp[lo][hi];
        boolean result = s.charAt(lo) == s.charAt(hi) && isPalindrome(lo + 1, hi - 1);
        palDp[lo][hi] = result;
        return result;
    }
}
