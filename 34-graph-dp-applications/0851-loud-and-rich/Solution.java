import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 851. Loud and Rich
 * Approach: Top-down memoized recursion over the "richer than" DAG.
 * richer[i] = [a, b] means a has more money than b, i.e. an edge a -> b;
 * to answer "who is the quietest among everyone at least as rich as x" we
 * need to walk BACKWARD along these edges from x (toward richer people).
 * dp[x] = the quietest person among {x} union the quietest answers of
 * everyone directly richer than x -- each candidate's own answer is
 * itself memoized, so no person's richer-chain is recomputed twice even
 * though multiple people can share richer ancestors.
 * Time: O(V+E) | Space: O(V+E)
 */
class Solution {
    private List<List<Integer>> richerThan; // richerThan[x] = people directly richer than x
    private int[] quiet;
    private Integer[] dp;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        this.quiet = quiet;
        richerThan = new ArrayList<>();
        for (int i = 0; i < n; i++) richerThan.add(new ArrayList<>());
        for (int[] r : richer) {
            richerThan.get(r[1]).add(r[0]);
        }

        dp = new Integer[n];
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) answer[i] = solve(i);
        return answer;
    }

    private int solve(int x) {
        if (dp[x] != null) return dp[x];
        int quietest = x;
        for (int richerPerson : richerThan.get(x)) {
            int candidate = solve(richerPerson);
            if (quiet[candidate] < quiet[quietest]) quietest = candidate;
        }
        dp[x] = quietest;
        return quietest;
    }
}
