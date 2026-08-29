import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1654. Minimum Jumps to Reach Home
 * Approach: Not a natural fit for top-down memoized recursion -- the
 * state graph over (position, justJumpedBackward) is cyclic (forward and
 * backward jumps can revisit positions), so this needs a genuine
 * shortest-path search rather than a DAG-shaped recursion. Multi-source-
 * free BFS explores (position, justJumpedBackward) states, bounded by a
 * safe ceiling (2*(furthest forbidden position) + a + b) beyond which
 * jumping further is never useful.
 * Time: O(bound) | Space: O(bound)
 */
class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenSet = new HashSet<>();
        int maxForbidden = 0;
        for (int f : forbidden) {
            forbiddenSet.add(f);
            maxForbidden = Math.max(maxForbidden, f);
        }
        int bound = 2 * Math.max(maxForbidden, x) + a + b + 1;

        boolean[][] visited = new boolean[bound + 1][2]; // [position][justJumpedBack]
        Deque<int[]> queue = new ArrayDeque<>(); // {position, justJumpedBack, jumps}
        queue.add(new int[]{0, 0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int pos = state[0], justBack = state[1], jumps = state[2];
            if (pos == x) return jumps;

            int forward = pos + a;
            if (forward <= bound && !forbiddenSet.contains(forward) && !visited[forward][0]) {
                visited[forward][0] = true;
                queue.add(new int[]{forward, 0, jumps + 1});
            }
            if (justBack == 0) {
                int backward = pos - b;
                if (backward >= 0 && !forbiddenSet.contains(backward) && !visited[backward][1]) {
                    visited[backward][1] = true;
                    queue.add(new int[]{backward, 1, jumps + 1});
                }
            }
        }
        return -1;
    }
}
