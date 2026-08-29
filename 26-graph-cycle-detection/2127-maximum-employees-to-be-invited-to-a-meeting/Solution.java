import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 2127. Maximum Employees to Be Invited to a Meeting
 * Approach: This is a functional graph (each node has exactly one
 * outgoing edge), so it decomposes into trees hanging off cycles.
 * Kahn's-algorithm-style peeling of indegree-0 nodes strips away every
 * non-cycle node while recording chainLen[v] = the longest chain of
 * "admirers" feeding into v. What's left are pure cycles: any cycle
 * longer than 2 can seat everyone around one big round table (a valid
 * answer on its own); every mutual pair (a 2-cycle) can instead be
 * combined with its two longest incoming chains into one long table,
 * and multiple such pairs can all sit at the SAME table back to back.
 * The answer is the larger of the single longest cycle and the total
 * from combining all 2-cycles.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] indegree = new int[n];
        for (int f : favorite) indegree[f]++;

        int[] chainLen = new int[n];
        boolean[] removed = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            removed[node] = true;
            int next = favorite[node];
            chainLen[next] = Math.max(chainLen[next], chainLen[node] + 1);
            if (--indegree[next] == 0) queue.add(next);
        }

        int longestCycle = 0;
        long twoCycleSum = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (removed[i] || visited[i]) continue;
            List<Integer> cycleNodes = new ArrayList<>();
            int cur = i;
            while (!visited[cur]) {
                visited[cur] = true;
                cycleNodes.add(cur);
                cur = favorite[cur];
            }
            if (cycleNodes.size() == 2) {
                int a = cycleNodes.get(0), b = cycleNodes.get(1);
                twoCycleSum += 2 + chainLen[a] + chainLen[b];
            } else {
                longestCycle = Math.max(longestCycle, cycleNodes.size());
            }
        }
        return (int) Math.max(longestCycle, twoCycleSum);
    }
}
