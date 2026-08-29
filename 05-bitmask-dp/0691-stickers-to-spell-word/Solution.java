/**
 * LeetCode 691. Stickers to Spell Word
 * Approach: Top-down memoized recursion over `mask` (which letters of
 * target are already covered). For each sticker, greedily apply its
 * letters to the leftmost uncovered target positions that match, producing
 * a new mask; trying every sticker and taking the best gives the min count.
 * Time: O(2^n * stickers * n) | Space: O(2^n)
 */
class Solution {
    private String[] stickers;
    private String target;
    private int n;
    private Integer[] dp;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int minStickers(String[] stickers, String target) {
        this.stickers = stickers;
        this.target = target;
        this.n = target.length();
        this.dp = new Integer[1 << n];
        int result = solve(0);
        return result >= INF ? -1 : result;
    }

    private int solve(int mask) {
        int full = (1 << n) - 1;
        if (mask == full) return 0;
        if (dp[mask] != null) return dp[mask];
        int best = INF;
        for (String sticker : stickers) {
            int[] avail = new int[26];
            for (char c : sticker.toCharArray()) avail[c - 'a']++;

            int newMask = mask;
            for (int i = 0; i < n; i++) {
                if ((newMask & (1 << i)) != 0) continue;
                char c = target.charAt(i);
                if (avail[c - 'a'] > 0) {
                    avail[c - 'a']--;
                    newMask |= (1 << i);
                }
            }
            if (newMask != mask) { // sticker made progress
                best = Math.min(best, 1 + solve(newMask));
            }
        }
        dp[mask] = best;
        return best;
    }
}
