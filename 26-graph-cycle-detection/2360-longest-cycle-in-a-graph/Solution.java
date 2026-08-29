import java.util.Arrays;

/**
 * LeetCode 2360. Longest Cycle in a Graph
 * Approach: Each node has at most one outgoing edge, so walking from an
 * unvisited node either dead-ends (edges[cur] == -1) or eventually hits
 * an already-visited node. If that node was visited during THIS walk
 * (its visit-time is >= this walk's start time), the walk just closed a
 * cycle whose length is the current timer minus that node's visit time.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] visitTime = new int[n];
        Arrays.fill(visitTime, -1);
        int timer = 0;
        int best = -1;

        for (int i = 0; i < n; i++) {
            if (visitTime[i] != -1) continue;
            int startTime = timer;
            int cur = i;
            while (cur != -1 && visitTime[cur] == -1) {
                visitTime[cur] = timer++;
                cur = edges[cur];
            }
            if (cur != -1 && visitTime[cur] >= startTime) {
                best = Math.max(best, timer - visitTime[cur]);
            }
        }
        return best;
    }
}
