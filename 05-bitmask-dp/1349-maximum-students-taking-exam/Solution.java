/**
 * LeetCode 1349. Maximum Students Taking Exam
 * Approach: Top-down memoized recursion over (row, prevRowMask) -- try
 * every legal seating mask for the current row (only broken-seat-free
 * positions, no two horizontally adjacent seats) that also doesn't
 * diagonally conflict with the previous row's seating, and take the best
 * over all valid current-row masks plus the best for the rows below.
 * Time: O(rows * 4^cols) | Space: O(rows * 2^cols)
 */
class Solution {
    private char[][] seats;
    private int rows, cols;
    private int[] validRowMasks; // per row: bitmask of usable (non-broken) seats, precomputed
    private Integer[][] dp;

    public int maxStudents(char[][] seats) {
        this.seats = seats;
        this.rows = seats.length;
        this.cols = seats[0].length;
        this.validRowMasks = new int[rows];
        for (int r = 0; r < rows; r++) {
            int mask = 0;
            for (int c = 0; c < cols; c++) {
                if (seats[r][c] == '.') mask |= (1 << c);
            }
            validRowMasks[r] = mask;
        }
        this.dp = new Integer[rows][1 << cols];
        return solve(0, 0);
    }

    private int solve(int row, int prevMask) {
        if (row == rows) return 0;
        if (dp[row][prevMask] != null) return dp[row][prevMask];
        int best = 0;
        int usable = validRowMasks[row];
        for (int mask = usable; ; mask = (mask - 1) & usable) {
            if (isValidRow(mask) && !conflictsDiagonally(mask, prevMask)) {
                best = Math.max(best, Integer.bitCount(mask) + solve(row + 1, mask));
            }
            if (mask == 0) break;
        }
        dp[row][prevMask] = best;
        return best;
    }

    private boolean isValidRow(int mask) {
        return (mask & (mask << 1)) == 0; // no two horizontally adjacent seats
    }

    private boolean conflictsDiagonally(int mask, int prevMask) {
        return (mask & (prevMask << 1)) != 0 || (mask & (prevMask >> 1)) != 0;
    }
}
