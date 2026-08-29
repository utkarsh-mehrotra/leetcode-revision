/**
 * LeetCode 518. Coin Change 2
 * Approach: Top-down memoized recursion over (coinIndex, remaining) --
 * ways(i, r) either skips coin i or reuses it (staying at index i to allow
 * repetition), which counts combinations rather than permutations.
 * Time: O(amount * coins.length) | Space: O(amount * coins.length)
 */
class Solution {
    private int[] coins;
    private Integer[][] memo;

    public int change(int amount, int[] coins) {
        this.coins = coins;
        this.memo = new Integer[coins.length + 1][amount + 1];
        return ways(0, amount);
    }

    private int ways(int i, int remaining) {
        if (remaining == 0) return 1;
        if (i == coins.length || remaining < 0) return 0;
        if (memo[i][remaining] != null) return memo[i][remaining];
        int result = ways(i + 1, remaining) + ways(i, remaining - coins[i]);
        memo[i][remaining] = result;
        return result;
    }
}
