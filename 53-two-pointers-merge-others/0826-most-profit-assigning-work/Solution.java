import java.util.Arrays;

/**
 * LeetCode 826. Most Profit Assigning Work
 * Approach: Sort jobs by difficulty and workers by ability, then two
 * pointers sweep both from the start together: as the worker pointer
 * advances to higher abilities, the job pointer only ever moves forward
 * too, absorbing every newly-affordable job into a running best-profit
 * tracker (a worker who can do a hard, low-paying job can always do an
 * easier one instead, so only the best profit among affordable jobs
 * matters). Each worker is paid that running best.
 * Time: O(n log n + m log m) | Space: O(n) for the paired job array
 */
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> difficulty[a] - difficulty[b]);
        Arrays.sort(worker);

        int jobIdx = 0, bestProfitSoFar = 0, totalProfit = 0;
        for (int ability : worker) {
            while (jobIdx < n && difficulty[order[jobIdx]] <= ability) {
                bestProfitSoFar = Math.max(bestProfitSoFar, profit[order[jobIdx]]);
                jobIdx++;
            }
            totalProfit += bestProfitSoFar;
        }
        return totalProfit;
    }
}
