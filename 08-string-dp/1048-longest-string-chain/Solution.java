import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 1048. Longest String Chain
 * Approach: Top-down memoized recursion -- chainLength(word) tries
 * removing each single character to form a predecessor; if that
 * predecessor exists in the word set, the chain through it extends by 1.
 * Time: O(n * maxLen^2) | Space: O(n)
 */
class Solution {
    private Set<String> wordSet;
    private Map<String, Integer> dp;

    public int longestStrChain(String[] words) {
        wordSet = new HashSet<>(Arrays.asList(words));
        dp = new HashMap<>();
        int best = 1;
        for (String word : words) {
            best = Math.max(best, chainLength(word));
        }
        return best;
    }

    private int chainLength(String word) {
        if (dp.containsKey(word)) return dp.get(word);
        int best = 1;
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            String predecessor = sb.substring(0, i) + sb.substring(i + 1);
            if (wordSet.contains(predecessor)) {
                best = Math.max(best, 1 + chainLength(predecessor));
            }
        }
        dp.put(word, best);
        return best;
    }
}
