/**
 * LeetCode 1278. Palindrome Partitioning III
 * Approach: Top-down memoized recursion -- best(i, k) is the fewest
 * character changes to split s[i:] into exactly k palindromic pieces.
 * changeCost(lo, hi), the changes needed to make s[lo..hi] a palindrome,
 * is memoized separately via a two-pointer mismatch count.
 * Time: O(n^2 * k + n^2) | Space: O(n^2 + n * k)
 */
class Solution {
    private String s;
    private int n;
    private Integer[][] costDp;
    private Integer[][] bestDp;

    public int palindromePartition(String s, int k) {
        this.s = s;
        this.n = s.length();
        this.costDp = new Integer[n][n];
        this.bestDp = new Integer[n][k + 1];
        return best(0, k);
    }

    private int best(int i, int k) {
        if (k == 1) return changeCost(i, n - 1);
        if (bestDp[i][k] != null) return bestDp[i][k];
        int result = Integer.MAX_VALUE;
        for (int j = i; j <= n - k; j++) {
            result = Math.min(result, changeCost(i, j) + best(j + 1, k - 1));
        }
        bestDp[i][k] = result;
        return result;
    }

    private int changeCost(int lo, int hi) {
        if (lo >= hi) return 0;
        if (costDp[lo][hi] != null) return costDp[lo][hi];
        int result = (s.charAt(lo) == s.charAt(hi) ? 0 : 1) + changeCost(lo + 1, hi - 1);
        costDp[lo][hi] = result;
        return result;
    }
}
