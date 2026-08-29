/**
 * LeetCode 1510. Stone Game IV
 * Approach: Boolean DP -- win[i] is true if the player to move with i
 * stones remaining can force a win. win[i] is true if some perfect square
 * s <= i leaves the opponent at a losing state win[i - s] == false.
 * Time: O(n * sqrt(n)) | Space: O(n)
 */
class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] win = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                if (!win[i - k * k]) {
                    win[i] = true;
                    break;
                }
            }
        }
        return win[n];
    }
}
