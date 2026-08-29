import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 207. Course Schedule
 * Approach: Kahn's algorithm -- repeatedly remove indegree-0 nodes and
 * decrement their neighbors' indegree. All courses are completable iff
 * every node gets removed this way; any node still stuck with indegree
 * > 0 at the end means it's part of a cycle.
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]);
            indegree[p[0]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            visited++;
            for (int next : adj.get(course)) {
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        return visited == numCourses;
    }
}
