/**
 * LeetCode 1025. Divisor Game
 * Approach: Top-down memoized game-theory recursion -- win(n) is true if
 * some proper divisor x of n leaves the opponent facing a losing position
 * win(n - x) == false.
 * Time: O(n * d(n)) | Space: O(n)
 */
class Solution {
    private Boolean[] memo;

    public boolean divisorGame(int n) {
        memo = new Boolean[n + 1];
        return win(n);
    }

    private boolean win(int n) {
        if (n <= 1) return false;
        if (memo[n] != null) return memo[n];
        boolean result = false;
        for (int x = 1; x < n; x++) {
            if (n % x == 0 && !win(n - x)) {
                result = true;
                break;
            }
        }
        memo[n] = result;
        return result;
    }
}
