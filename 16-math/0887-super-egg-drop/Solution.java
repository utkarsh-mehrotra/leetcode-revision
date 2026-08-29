/**
 * LeetCode 887. Super Egg Drop
 * Approach: Rather than memoizing (eggs, floors) -> min moves directly
 * (too slow for large n), invert the question: maxFloors(eggs, moves) is
 * the most floors distinguishable with a given move budget -- dropping
 * once splits into "breaks" (maxFloors(eggs-1, moves-1) floors above)
 * and "survives" (maxFloors(eggs, moves-1) floors below), plus the
 * floor tested itself. Top-down memoized recursion on that, then the
 * answer is the smallest move count whose max floors reaches n.
 * Time: O(eggs * log n) | Space: O(eggs * log n)
 */
class Solution {
    private Integer[][] dp;

    public int superEggDrop(int k, int n) {
        dp = new Integer[k + 1][];
        int moves = 0;
        while (maxFloors(k, moves) < n) {
            moves++;
        }
        return moves;
    }

    private int maxFloors(int eggs, int moves) {
        if (eggs == 0 || moves == 0) return 0;
        if (dp[eggs] == null) dp[eggs] = new Integer[moves + 1];
        else if (moves >= dp[eggs].length) {
            dp[eggs] = java.util.Arrays.copyOf(dp[eggs], moves + 1);
        }
        if (dp[eggs][moves] != null) return dp[eggs][moves];
        int result = 1 + maxFloors(eggs - 1, moves - 1) + maxFloors(eggs, moves - 1);
        dp[eggs][moves] = result;
        return result;
    }
}
