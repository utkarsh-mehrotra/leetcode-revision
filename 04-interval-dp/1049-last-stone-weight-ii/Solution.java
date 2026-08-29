/**
 * LeetCode 1049. Last Stone Weight II
 * Approach: Smashing stones is equivalent to splitting them into two
 * groups and taking the absolute difference of their sums, so this
 * reduces to a knapsack: top-down memoized recursion best(i, capacity)
 * finds the largest achievable subset sum not exceeding half the total.
 * The answer is total - 2 * best(0, total/2).
 * Time: O(n * sum) | Space: O(n * sum)
 */
class Solution {
    private int[] stones;
    private Integer[][] dp;

    public int lastStoneWeightII(int[] stones) {
        this.stones = stones;
        int total = 0;
        for (int stone : stones) total += stone;
        int half = total / 2;
        this.dp = new Integer[stones.length][half + 1];
        int bestSubsetSum = best(0, half);
        return total - 2 * bestSubsetSum;
    }

    private int best(int i, int capacity) {
        if (i == stones.length) return 0;
        if (dp[i][capacity] != null) return dp[i][capacity];
        int result = best(i + 1, capacity); // skip this stone
        if (stones[i] <= capacity) {
            result = Math.max(result, stones[i] + best(i + 1, capacity - stones[i]));
        }
        dp[i][capacity] = result;
        return result;
    }
}
