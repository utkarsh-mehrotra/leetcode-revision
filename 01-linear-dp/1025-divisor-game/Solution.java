/**
 * LeetCode 1025. Divisor Game
 * Approach: Top-down memoized game-theory recursion -- win(n) is true if
 * some proper divisor x of n leaves the opponent facing a losing position
 * win(n - x) == false.
 * Time: O(n * d(n)) | Space: O(n)
 */
class Solution {
    private Boolean[] dp;

    public boolean divisorGame(int n) {
        dp = new Boolean[n + 1];
        return win(n);
    }

    private boolean win(int n) {
        if (n <= 1) return false;
        if (dp[n] != null) return dp[n];
        boolean result = false;
        for (int x = 1; x < n; x++) { // try every proper divisor x of n as a legal move
            if (n % x == 0 && !win(n - x)) {
                result = true;
                break;
            }
        }
        dp[n] = result;
        return result;
    }
}
