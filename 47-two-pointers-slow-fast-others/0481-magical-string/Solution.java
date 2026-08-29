/**
 * LeetCode 481. Magical String
 * Approach: Classic slow/fast pointer generator. The magical string
 * describes its own run-lengths: a slow "reading" pointer `i` walks the
 * already-generated string, and each value it sees tells how many copies
 * of the next alternating value (1, 2, 1, 2, ...) to append at the fast
 * "writing" position. Seeded with the known first three characters
 * "122", the sequence bootstraps itself forward until n characters exist.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1; // "1", "12", "122" each contain exactly one '1'

        int[] s = new int[n];
        s[0] = 1;
        s[1] = 2;
        s[2] = 2;
        int count = 3; // fast pointer: next write position
        int nextVal = 1;
        for (int i = 2; count < n; i++) { // slow pointer: read position
            int runLength = s[i];
            for (int k = 0; k < runLength && count < n; k++) {
                s[count++] = nextVal;
            }
            nextVal = 3 - nextVal;
        }

        int ones = 0;
        for (int v : s) {
            if (v == 1) ones++;
        }
        return ones;
    }
}
