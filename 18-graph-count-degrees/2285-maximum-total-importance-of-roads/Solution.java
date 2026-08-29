/**
 * LeetCode 2285. Maximum Total Importance of Roads
 * Approach: The total importance sum(degree[i] * label[i]) is maximized
 * by a rearrangement argument: assign the largest label to the
 * highest-degree city, the next largest to the next, and so on --
 * swapping any two labels out of degree order would only decrease the sum.
 * Time: O(n log n + roads) | Space: O(n)
 */
class Solution {
    public long maximumImportance(int n, int[][] roads) {
        int[] degree = new int[n];
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
        }
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        java.util.Arrays.sort(order, (a, b) -> degree[a] - degree[b]);

        long total = 0;
        long label = 1;
        for (int city : order) {
            total += (long) degree[city] * label;
            label++;
        }
        return total;
    }
}
