/**
 * LeetCode 1601. Maximum Number of Achievable Transfer Requests
 * Approach: Not a natural fit for memoized recursion -- validity depends
 * on a full per-building degree-balance array rather than a small,
 * reusable state, so there's no overlapping subproblem to cache. Instead
 * this is plain recursive backtracking: for each request, try including
 * it (mutating in/out degree counts) or skipping it, keeping the best
 * fully-balanced count found once every request has been decided, with a
 * simple upper-bound prune.
 * Time: O(2^m) | Space: O(n) recursion state (degree array)
 */
class Solution {
    private int[][] requests;
    private int[] degree;
    private int best;

    public int maximumRequests(int n, int[][] requests) {
        this.requests = requests;
        this.degree = new int[n];
        this.best = 0;
        backtrack(0, 0);
        return best;
    }

    private void backtrack(int i, int chosenCount) {
        if (i == requests.length) {
            for (int d : degree) if (d != 0) return;
            best = Math.max(best, chosenCount);
            return;
        }
        if (chosenCount + (requests.length - i) <= best) return; // can't beat best even taking the rest

        int from = requests[i][0], to = requests[i][1];
        degree[from]--;
        degree[to]++;
        backtrack(i + 1, chosenCount + 1);
        degree[from]++;
        degree[to]--;

        backtrack(i + 1, chosenCount);
    }
}
