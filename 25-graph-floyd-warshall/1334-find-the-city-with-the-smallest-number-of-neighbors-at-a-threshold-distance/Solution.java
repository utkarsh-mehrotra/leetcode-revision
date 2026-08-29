/**
 * LeetCode 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * Approach: Floyd-Warshall computes all-pairs shortest distances (n is
 * small enough for O(n^3)); then count, per city, how many others are
 * within the threshold, and pick the city with the fewest (largest
 * index breaks ties).
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        long[][] dist = new long[n][n];
        for (long[] row : dist) java.util.Arrays.fill(row, Long.MAX_VALUE / 2);
        for (int i = 0; i < n; i++) dist[i][i] = 0;
        for (int[] e : edges) {
            dist[e[0]][e[1]] = e[2];
            dist[e[1]][e[0]] = e[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int bestCity = -1, bestCount = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) count++;
            }
            if (count <= bestCount) {
                bestCount = count;
                bestCity = i;
            }
        }
        return bestCity;
    }
}
