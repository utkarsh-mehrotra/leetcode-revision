import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 1462. Course Schedule IV
 * Approach: Kahn's-algorithm topological order guarantees every
 * prerequisite of a course is finalized before the course itself is
 * popped; propagate each course's full reachable-prerequisite set (as a
 * bitset) forward to its direct successors. A query (u, v) is true iff
 * u is in v's accumulated set.
 * Time: O((V + E) * V / 64) | Space: O(V^2 / 64)
 */
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] p : prerequisites) {
            adj.get(p[0]).add(p[1]);
            indegree[p[1]]++;
        }

        BitSet[] reachablePrereqs = new BitSet[numCourses];
        for (int i = 0; i < numCourses; i++) reachablePrereqs[i] = new BitSet(numCourses);

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : adj.get(node)) {
                reachablePrereqs[next].set(node);
                reachablePrereqs[next].or(reachablePrereqs[node]);
                if (--indegree[next] == 0) queue.add(next);
            }
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] q : queries) {
            result.add(reachablePrereqs[q[1]].get(q[0]));
        }
        return result;
    }
}
