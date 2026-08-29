/**
 * LeetCode 790. Domino and Tromino Tiling
 * Approach: Let f(n) be the number of ways to fully tile a 2 x n board.
 * The known recurrence is f(n) = 2*f(n-1) + f(n-3), derived from casework
 * on how the rightmost column(s) are covered (a full domino column, or a
 * tromino completing a partially filled column together with an earlier gap).
 * Time: O(n) | Space: O(1)
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int numTilings(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        long a = 1, b = 2, c = 5; // f(1), f(2), f(3)
        for (int i = 4; i <= n; i++) {
            long d = (2 * c % MOD + a) % MOD;
            a = b;
            b = c;
            c = d;
        }
        return (int) c;
    }
}
