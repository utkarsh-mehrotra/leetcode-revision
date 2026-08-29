import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 472. Concatenated Words
 * Approach: Top-down memoized recursion (per word) -- canBuild(word) is
 * true if word can be split into 2 or more pieces that are each present
 * in the dictionary (the whole word itself doesn't count as a piece).
 * Time: O(totalChars * maxWordLen) | Space: O(totalChars)
 */
class Solution {
    private Set<String> dict;
    private Map<String, Boolean> dp;

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        dict = new HashSet<>();
        for (String w : words) dict.add(w);
        dp = new HashMap<>();
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (!word.isEmpty() && canBuild(word, 0, true)) {
                result.add(word);
            }
        }
        return result;
    }

    // isFullWord: true only for the very first call on this word (so we
    // require at least one split before allowing a full dictionary match).
    private boolean canBuild(String word, int start, boolean isFullWord) {
        if (start == word.length()) return !isFullWord;
        String key = word.substring(start);
        if (!isFullWord && dp.containsKey(key)) return dp.get(key);

        boolean result = false;
        for (int end = start + 1; end <= word.length(); end++) {
            boolean isLastPiece = (end == word.length());
            String piece = word.substring(start, end);
            if (dict.contains(piece) && !(isFullWord && start == 0 && isLastPiece)) {
                if (canBuild(word, end, false)) {
                    result = true;
                    break;
                }
            }
        }
        if (!isFullWord) dp.put(key, result);
        return result;
    }
}
