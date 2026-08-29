import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 139. Word Break
 * Approach: Top-down memoized recursion -- canBreak(i) is true if s[i:]
 * can be segmented into dictionary words, tried by matching every
 * dictionary word as the next piece.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private String s;
    private Set<String> dict;
    private Boolean[] dp;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.s = s;
        this.dict = new HashSet<>(wordDict);
        this.dp = new Boolean[s.length() + 1];
        return canBreak(0);
    }

    private boolean canBreak(int i) {
        if (i == s.length()) return true;
        if (dp[i] != null) return dp[i];
        boolean result = false;
        for (int end = i + 1; end <= s.length(); end++) {
            if (dict.contains(s.substring(i, end)) && canBreak(end)) {
                result = true;
                break;
            }
        }
        dp[i] = result;
        return result;
    }
}
