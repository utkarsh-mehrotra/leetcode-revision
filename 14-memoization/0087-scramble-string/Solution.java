import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 87. Scramble String
 * Approach: Top-down memoized recursion over (i1, i2, len) -- two length-
 * len substrings (starting at i1 in s1, i2 in s2) are scrambles of each
 * other if they're equal outright, or if some split point k lets either
 * the non-swapped pairing (first k chars vs first k chars) or the
 * swapped pairing (first k chars of one vs last k of the other) both
 * recursively match.
 * Time: O(n^4) | Space: O(n^3) distinct states
 */
class Solution {
    private String s1, s2;
    private Map<String, Boolean> dp;

    public boolean isScramble(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.dp = new HashMap<>();
        return solve(0, 0, s1.length());
    }

    private boolean solve(int i1, int i2, int len) {
        String key = i1 + "," + i2 + "," + len;
        Boolean cached = dp.get(key);
        if (cached != null) return cached;

        if (s1.regionMatches(i1, s2, i2, len)) {
            dp.put(key, true);
            return true;
        }
        if (!sameLetters(i1, i2, len)) {
            dp.put(key, false);
            return false;
        }

        boolean result = false;
        for (int k = 1; k < len && !result; k++) {
            boolean noSwap = solve(i1, i2, k) && solve(i1 + k, i2 + k, len - k);
            boolean swap = solve(i1, i2 + len - k, k) && solve(i1 + k, i2, len - k);
            result = noSwap || swap;
        }
        dp.put(key, result);
        return result;
    }

    private boolean sameLetters(int i1, int i2, int len) {
        int[] counts = new int[26];
        for (int i = 0; i < len; i++) {
            counts[s1.charAt(i1 + i) - 'a']++;
            counts[s2.charAt(i2 + i) - 'a']--;
        }
        for (int c : counts) if (c != 0) return false;
        return true;
    }
}
