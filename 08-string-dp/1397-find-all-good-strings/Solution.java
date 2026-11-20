/**
 * LeetCode 1616. Find All Good Strings
 * Approach: KMP-automaton digit DP. Build the standard KMP failure
 * function for `evil`, then a transition table automaton[state][c] giving
 * the new "longest evil-prefix matched" state after appending c. Top-down
 * memoized recursion over (pos, evilState, tightLow, tightHigh) tries
 * every character within the currently allowed [low, high] bound, skips
 * any that would complete `evil`, and only consults the memo once neither
 * bound is still active (the subproblem no longer depends on s1/s2's exact
 * digits from there).
 * Time: O(n * m * 26) | Space: O(n * m)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private String s1, s2, evil;
    private int n, m;
    private int[][] automaton;
    private Integer[][] dp;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.n = n;
        this.s1 = s1;
        this.s2 = s2;
        this.evil = evil;
        this.m = evil.length();
        buildAutomaton();
        this.dp = new Integer[n + 1][m];
        return solve(0, 0, true, true);
    }

    private void buildAutomaton() {
        int[] fail = new int[m];
        int k = 0;
        for (int i = 1; i < m; i++) {
            while (k > 0 && evil.charAt(i) != evil.charAt(k)) k = fail[k - 1];
            if (evil.charAt(i) == evil.charAt(k)) k++;
            fail[i] = k;
        }
        automaton = new int[m][26];
        for (int state = 0; state < m; state++) {
            for (int c = 0; c < 26; c++) {
                char ch = (char) ('a' + c);
                int k2 = state;
                while (k2 > 0 && ch != evil.charAt(k2)) k2 = fail[k2 - 1];
                if (ch == evil.charAt(k2)) k2++;
                automaton[state][c] = k2;
            }
        }
    }

    private int solve(int pos, int evilState, boolean tightLow, boolean tightHigh) {
        if (evilState == m) return 0; // evil was matched somewhere in the prefix
        if (pos == n) return 1;
        if (!tightLow && !tightHigh && dp[pos][evilState] != null) return dp[pos][evilState];
        char lo = tightLow ? s1.charAt(pos) : 'a';
        char hi = tightHigh ? s2.charAt(pos) : 'z';
        long total = 0;
        for (char c = lo; c <= hi; c++) {
            int nextState = automaton[evilState][c - 'a'];
            if (nextState == m) continue;
            total = (total + solve(pos + 1, nextState, tightLow && c == lo, tightHigh && c == hi)) % MOD;
        }
        if (!tightLow && !tightHigh) dp[pos][evilState] = (int) total;
        return (int) total;
    }
}
