/**
 * LeetCode 1643. Kth Smallest Instructions
 * Approach: Build the path greedily, one instruction at a time. At each
 * step, the number of lexicographically-smaller-or-equal completions
 * starting with 'H' equals C(remainingH - 1 + remainingV, remainingV) --
 * a combinatorics count computed via a memoized Pascal's-triangle
 * recursion. If k falls within that count, commit to 'H'; otherwise
 * subtract it out and commit to 'V'.
 * Time: O((rows+cols)^2) | Space: O((rows+cols)^2)
 */
class Solution {
    private Long[][] chooseDp;

    public String kthSmallestPath(int[] destination, int k) {
        int v = destination[0], h = destination[1];
        int maxN = v + h;
        chooseDp = new Long[maxN + 1][maxN + 1];

        StringBuilder sb = new StringBuilder();
        long remaining = k;
        while (h > 0 || v > 0) {
            if (h > 0) {
                long countStartingH = choose(h - 1 + v, v);
                if (remaining <= countStartingH) {
                    sb.append('H');
                    h--;
                    continue;
                }
                remaining -= countStartingH;
            }
            sb.append('V');
            v--;
        }
        return sb.toString();
    }

    private long choose(int a, int b) {
        if (b == 0 || b == a) return 1;
        if (chooseDp[a][b] != null) return chooseDp[a][b];
        long result = choose(a - 1, b - 1) + choose(a - 1, b);
        chooseDp[a][b] = result;
        return result;
    }
}
