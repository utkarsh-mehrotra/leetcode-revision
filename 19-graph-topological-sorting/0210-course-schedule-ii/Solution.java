import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 210. Course Schedule II
 * Approach: Kahn's algorithm -- repeatedly remove indegree-0 nodes,
 * recording the removal order (a valid topological / completion order),
 * decrementing neighbors' indegree as each is removed. If not every
 * course gets removed, a cycle exists and no order is possible.
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        int[] order = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[idx++] = course;
            for (int next : adj.get(course)) {
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        return idx == numCourses ? order : new int[0];
    }
}
