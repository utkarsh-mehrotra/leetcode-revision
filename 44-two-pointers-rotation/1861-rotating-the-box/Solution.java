/**
 * LeetCode 1861. Rotating the Box
 * Approach: Two steps. First, simulate gravity on each row with a
 * "settle" two-pointer scan from the right: `writePos` tracks where the
 * next falling stone lands, resetting past any obstacle ('*') and
 * advancing left to right, so all stones ('#') slide as far right as
 * possible without crossing an obstacle. Second, rotate the settled grid
 * 90 degrees clockwise via direct index mapping (rotated[c][rows-1-r] =
 * original[r][c]) -- a fixed transformation, not a two-pointer step.
 * Time: O(rows*cols) | Space: O(rows*cols) output
 */
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length, cols = box[0].length;
        for (char[] row : box) {
            int writePos = cols - 1;
            for (int c = cols - 1; c >= 0; c--) {
                if (row[c] == '*') {
                    writePos = c - 1;
                } else if (row[c] == '#') {
                    row[c] = '.';
                    row[writePos] = '#';
                    writePos--;
                }
            }
        }

        char[][] rotated = new char[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = box[r][c];
            }
        }
        return rotated;
    }
}
