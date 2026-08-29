import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 940. Distinct Subsequences II
 * Approach: Top-down memoized recursion by prefix length -- total(i) is
 * the number of distinct subsequences of s[0:i]. Appending s[i-1] doubles
 * every previous subsequence (with and without the new character), except
 * that re-appending the same character to subsequences that already ended
 * right before its LAST prior occurrence recreates duplicates, so that
 * count is subtracted back out. Recursing to i-1 first guarantees the
 * "last occurrence" lookup is fully up to date before total(i) is computed.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private String s;
    private Long[] dp;
    private Map<Character, Integer> lastOccurrence;

    public int distinctSubseqII(String s) {
        this.s = s;
        int n = s.length();
        this.dp = new Long[n + 1];
        this.lastOccurrence = new HashMap<>();
        long result = (total(n) - 1 + MOD) % MOD; // exclude the empty subsequence
        return (int) result;
    }

    private long total(int i) {
        if (i == 0) return 1;
        if (dp[i] != null) return dp[i];
        long prevTotal = total(i - 1); // ensures lastOccurrence is current through i-1
        char c = s.charAt(i - 1);
        long result = (2 * prevTotal) % MOD;
        Integer lastIdx = lastOccurrence.get(c);
        if (lastIdx != null) {
            result = (result - total(lastIdx - 1) + MOD) % MOD;
        }
        lastOccurrence.put(c, i);
        dp[i] = result;
        return result;
    }
}
