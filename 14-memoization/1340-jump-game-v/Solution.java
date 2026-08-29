/**
 * LeetCode 1340. Jump Game V
 * Approach: Top-down memoized recursion -- reach(i) is the max number of
 * indices visitable starting a jump-chain at i. A jump to j (within d
 * steps either direction) is legal only if every index strictly between
 * i and j is shorter than both endpoints' heights aren't required --
 * actually only arr[j] < arr[i] and no index strictly between is >=
 * arr[i]. Processing indices from shortest to tallest guarantees reach(j)
 * is already resolved whenever reach(i) needs it, since a legal jump only
 * ever goes to a strictly shorter position.
 * Time: O(n * d) | Space: O(n)
 */
class Solution {
    private int[] arr;
    private int d;
    private int n;
    private Integer[] dp;

    public int maxJumps(int[] arr, int d) {
        this.arr = arr;
        this.d = d;
        this.n = arr.length;
        this.dp = new Integer[n];

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        java.util.Arrays.sort(order, (a, b) -> arr[a] - arr[b]);

        int best = 1;
        for (int i : order) {
            best = Math.max(best, reach(i));
        }
        return best;
    }

    private int reach(int i) {
        if (dp[i] != null) return dp[i];
        int best = 1;
        for (int dir = -1; dir <= 1; dir += 2) {
            for (int step = 1; step <= d; step++) {
                int j = i + dir * step;
                if (j < 0 || j >= n || arr[j] >= arr[i]) break;
                best = Math.max(best, 1 + reach(j));
            }
        }
        dp[i] = best;
        return best;
    }
}
