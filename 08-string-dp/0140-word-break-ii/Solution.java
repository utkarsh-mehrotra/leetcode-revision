import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 140. Word Break II
 * Approach: Top-down memoized recursion -- sentencesFrom(i) is every way
 * to break s[i:] into dictionary words, joined with a trailing word each
 * time, built from sentencesFrom of whatever follows a matched word.
 * Time: O(n * 2^n) worst case | Space: O(n * 2^n) worst case
 */
class Solution {
    private String s;
    private Set<String> dict;
    private List<String>[] dp;

    @SuppressWarnings("unchecked")
    public List<String> wordBreak(String s, List<String> wordDict) {
        this.s = s;
        this.dict = new HashSet<>(wordDict);
        this.dp = new List[s.length() + 1];
        return sentencesFrom(0);
    }

    private List<String> sentencesFrom(int i) {
        if (dp[i] != null) return dp[i];
        List<String> result = new ArrayList<>();
        if (i == s.length()) {
            result.add("");
            dp[i] = result;
            return result;
        }
        for (int end = i + 1; end <= s.length(); end++) {
            String word = s.substring(i, end);
            if (!dict.contains(word)) continue;
            for (String rest : sentencesFrom(end)) {
                result.add(rest.isEmpty() ? word : word + " " + rest);
            }
        }
        dp[i] = result;
        return result;
    }
}
