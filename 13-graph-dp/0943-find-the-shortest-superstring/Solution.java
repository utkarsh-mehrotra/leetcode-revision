/**
 * LeetCode 943. Find the Shortest Superstring
 * Approach: Precompute overlap[i][j] = how many trailing characters of
 * word i coincide with leading characters of word j (so placing j right
 * after i saves that many characters). Top-down memoized recursion over
 * (usedMask, lastWord) -- the min extra length needed to append every
 * word not yet in usedMask, given word `lastWord` was placed most
 * recently. The actual string is rebuilt by replaying the same choice
 * the recursion made at each step.
 * Time: O(2^n * n^2) | Space: O(2^n * n)
 */
class Solution {
    private String[] words;
    private int n;
    private int[][] overlap;
    private Integer[][] dp;

    public String shortestSuperstring(String[] words) {
        this.words = words;
        this.n = words.length;
        overlap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                overlap[i][j] = bestOverlap(words[i], words[j]);
            }
        }
        dp = new Integer[1 << n][n];

        int fullMask = (1 << n) - 1;
        int bestLast = -1, bestCost = Integer.MAX_VALUE;
        for (int last = 0; last < n; last++) {
            int cost = extraCost(fullMask, last);
            if (cost < bestCost) {
                bestCost = cost;
                bestLast = last;
            }
        }

        // Rebuild the word order by replaying the recursion's choices.
        StringBuilder order = new StringBuilder();
        int mask = fullMask, last = bestLast;
        java.util.List<Integer> sequence = new java.util.ArrayList<>();
        while (mask != 0) {
            sequence.add(last);
            int prevMask = mask ^ (1 << last);
            if (prevMask == 0) break;
            int bestPrev = -1, bestPrevCost = Integer.MAX_VALUE;
            for (int prev = 0; prev < n; prev++) {
                if ((prevMask & (1 << prev)) == 0) continue;
                int candidate = extraCost(prevMask, prev) + words[last].length() - overlap[prev][last];
                if (candidate < bestPrevCost) {
                    bestPrevCost = candidate;
                    bestPrev = prev;
                }
            }
            mask = prevMask;
            last = bestPrev;
        }
        java.util.Collections.reverse(sequence);

        StringBuilder result = new StringBuilder(words[sequence.get(0)]);
        for (int idx = 1; idx < sequence.size(); idx++) {
            int prev = sequence.get(idx - 1), cur = sequence.get(idx);
            result.append(words[cur].substring(overlap[prev][cur]));
        }
        return result.toString();
    }

    // Min extra length to append every word in `mask` ending most recently with `last`.
    private int extraCost(int mask, int last) {
        if (mask == (1 << last)) return words[last].length();
        if (dp[mask][last] != null) return dp[mask][last];
        int prevMask = mask ^ (1 << last);
        int best = Integer.MAX_VALUE;
        for (int prev = 0; prev < n; prev++) {
            if ((prevMask & (1 << prev)) == 0) continue;
            int candidate = extraCost(prevMask, prev) + words[last].length() - overlap[prev][last];
            best = Math.min(best, candidate);
        }
        dp[mask][last] = best;
        return best;
    }

    private int bestOverlap(String a, String b) {
        int max = Math.min(a.length(), b.length());
        for (int len = max; len > 0; len--) {
            if (a.endsWith(b.substring(0, len))) return len;
        }
        return 0;
    }
}
