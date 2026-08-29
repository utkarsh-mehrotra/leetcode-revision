/**
 * LeetCode 1510. Stone Game IV
 * Approach: Top-down memoized recursion -- win(i) is true if the player to
 * move with i stones remaining can force a win by leaving the opponent at
 * some losing state win(i - s) == false for a perfect square s <= i.
 * Time: O(n * sqrt(n)) | Space: O(n)
 */
class Solution {
    private Boolean[] dp;

    public boolean winnerSquareGame(int n) {
        dp = new Boolean[n + 1];
        return win(n);
    }

    private boolean win(int i) {
        if (i == 0) return false;
        if (dp[i] != null) return dp[i];
        boolean result = false;
        for (int k = 1; k * k <= i; k++) {
            if (!win(i - k * k)) {
                result = true;
                break;
            }
        }
        dp[i] = result;
        return result;
    }
}
