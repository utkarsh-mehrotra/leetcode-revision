/**
 * LeetCode 1220. Count Vowels Permutation
 * Approach: Top-down memoized recursion over (length, lastVowel) -- the
 * count of valid strings of this length ending in lastVowel sums, over
 * every vowel legally allowed to precede it, the count of one-shorter
 * strings ending in that predecessor.
 * a<-{e,i,u}, e<-{a,i}, i<-{e,o}, o<-{i}, u<-{i,o}  (vowel -> its legal predecessors)
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int[][] PRED = {
        {1, 2, 4}, // a <- e, i, u
        {0, 2},    // e <- a, i
        {1, 3},    // i <- e, o
        {2},       // o <- i
        {2, 3}     // u <- i, o
    };
    private Long[][] dp;

    public int countVowelPermutation(int n) {
        dp = new Long[n + 1][5];
        long total = 0;
        for (int v = 0; v < 5; v++) {
            total = (total + count(n, v)) % MOD;
        }
        return (int) total;
    }

    private long count(int length, int vowel) {
        if (length == 1) return 1;
        if (dp[length][vowel] != null) return dp[length][vowel];
        long total = 0;
        for (int pred : PRED[vowel]) {
            total = (total + count(length - 1, pred)) % MOD;
        }
        dp[length][vowel] = total;
        return total;
    }
}
