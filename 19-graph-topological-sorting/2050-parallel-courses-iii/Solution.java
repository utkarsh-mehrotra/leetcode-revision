import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 2050. Parallel Courses III
 * Approach: Kahn's-algorithm topological order, tracking each course's
 * earliest possible finish time -- its own duration plus the latest
 * finish time among its prerequisites (all guaranteed final by the time
 * it's popped). The overall answer is the max finish time across every course.
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[n + 1];
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] r : relations) {
            adj.get(r[0]).add(r[1]);
            indegree[r[1]]++;
        }

        int[] finishTime = new int[n + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int course = 1; course <= n; course++) {
            if (indegree[course] == 0) {
                queue.add(course);
                finishTime[course] = time[course - 1];
            }
        }

        int result = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result = Math.max(result, finishTime[course]);
            for (int next : adj.get(course)) {
                finishTime[next] = Math.max(finishTime[next], finishTime[course] + time[next - 1]);
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        return result;
    }
}
