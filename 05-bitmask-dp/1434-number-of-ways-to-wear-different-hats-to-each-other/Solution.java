/**
 * LeetCode 1434. Number of Ways to Wear Different Hats to Each Other
 * Approach: Iterate hats 1..40 rather than people, since there are far
 * fewer people (<=10) than hats. Top-down memoized recursion over
 * (hat, peopleCoveredMask) -- either no one wears this hat, or it goes to
 * one of the people who like it and haven't been assigned a hat yet.
 * Time: O(hats * 2^people) | Space: O(hats * 2^people)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int NUM_HATS = 40;
    private int numPeople;
    private java.util.List<Integer>[] peopleWhoLike; // per hat (1-indexed via hat-1): people who like it
    private Long[][] dp;

    @SuppressWarnings("unchecked")
    public int numberWays(java.util.List<java.util.List<Integer>> hats) {
        numPeople = hats.size();
        peopleWhoLike = new java.util.List[NUM_HATS + 1];
        for (int h = 1; h <= NUM_HATS; h++) peopleWhoLike[h] = new java.util.ArrayList<>();
        for (int p = 0; p < numPeople; p++) {
            for (int hat : hats.get(p)) peopleWhoLike[hat].add(p);
        }
        dp = new Long[NUM_HATS + 1][1 << numPeople];
        return (int) solve(1, 0);
    }

    private long solve(int hat, int coveredMask) {
        int fullMask = (1 << numPeople) - 1;
        if (coveredMask == fullMask) return 1;
        if (hat > NUM_HATS) return 0;
        if (dp[hat][coveredMask] != null) return dp[hat][coveredMask];
        long total = solve(hat + 1, coveredMask); // no one takes this hat
        for (int person : peopleWhoLike[hat]) {
            if ((coveredMask & (1 << person)) == 0) {
                total = (total + solve(hat + 1, coveredMask | (1 << person))) % MOD;
            }
        }
        dp[hat][coveredMask] = total;
        return total;
    }
}
