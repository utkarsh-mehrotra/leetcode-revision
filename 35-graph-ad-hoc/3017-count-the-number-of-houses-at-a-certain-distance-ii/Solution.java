/**
 * LeetCode 3017. Count the Number of Houses at a Certain Distance II
 * (Note: this problem is mislabeled "3016" in some problem lists; its
 * real LeetCode number is 3017.)
 * Approach: The graph is a path 0..n-1 plus one extra edge (x,y). Instead
 * of computing each pair's shortest distance directly (O(n^2)), model
 * every starting node i as launching two BFS "walkers" that each advance
 * distance by 1 per step -- one walker implicitly covers the plain-path
 * distance in both directions at once (the `diff[0] += 2` seed). The
 * shortcut edge lets a walker jump from x to y (or vice versa) in one
 * extra step, so a NEW walker is forked at whichever of x/y is reached
 * first (the two `+= 1` terms). Because the two directions can now
 * overlap (a node can be reached both by the unshortcut route and by a
 * route through the shortcut), the tail ends (node 0, node n-1) and the
 * meeting point of the two shortcut-spawned walkers inside the x..y
 * segment must each have their double-counted route terminated (the four
 * `-= 1` terms). Every contribution is recorded as a delta at the
 * distance it occurs, then one prefix sum over the difference array
 * yields exact pair counts per distance in O(n) total.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        x--;
        y--;
        if (x > y) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[0] += 2; // baseline: two directions of travel from i along the plain path

            diff[Math.min(Math.abs(i - x), Math.abs(i - y) + 1)]++; // fork a walker at x
            diff[Math.min(Math.abs(i - y), Math.abs(i - x) + 1)]++; // fork a walker at y

            diff[Math.min(Math.abs(i - 0), Math.abs(i - y) + 1 + Math.abs(x - 0))]--; // route to node 0 ends
            diff[Math.min(Math.abs(i - (n - 1)), Math.abs(i - x) + 1 + Math.abs(y - (n - 1)))]--; // route to node n-1 ends

            // The two shortcut-spawned walkers meet inside the x..y segment; stop
            // whichever one would otherwise double-count past the midpoint.
            diff[Math.max(x - i, 0) + Math.max(i - y, 0) + (y - x) / 2]--;
            diff[Math.max(x - i, 0) + Math.max(i - y, 0) + (y - x + 1) / 2]--;
        }

        for (int i = 0; i < n - 1; i++) {
            diff[i + 1] += diff[i];
        }
        return diff;
    }
}
