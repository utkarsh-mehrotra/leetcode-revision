import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 913. Cat and Mouse
 * Approach: Zermelo's algorithm -- retrograde (reverse) BFS over the game
 * graph. A state is (mousePos, catPos, turn); rather than searching forward
 * from the start (which can cycle forever, hence draws), start from every
 * KNOWN terminal state -- mouse reaches the hole (Mouse wins) or cat
 * catches mouse (Cat wins) -- and propagate outcomes backward:
 *   - If ANY move from a predecessor state reaches a state where the mover
 *     wins, that predecessor is an immediate win for the mover.
 *   - If ALL of a predecessor's moves lead to the opponent winning, the
 *     predecessor is a forced loss (tracked via a per-state out-degree
 *     counter that must hit zero before concluding "forced").
 * States never resolved this way are true draws (infinite evasion is
 * possible). The cat may never step on node 0 (the hole), which is baked
 * into both its out-degree and the states we seed.
 * Time: O(n^2) states, O(n) edges each -> O(n^3) | Space: O(n^2)
 */
class Solution {
    private static final int DRAW = 0;
    private static final int MOUSE = 1;
    private static final int CAT = 2;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] color = new int[n][n][2];
        int[][][] degree = new int[n][n][2];
        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                degree[m][c][0] = graph[m].length;
                degree[m][c][1] = graph[c].length;
                for (int next : graph[c]) {
                    if (next == 0) degree[m][c][1]--; // cat may never enter the hole
                }
            }
        }

        // queue entries: {mouse, cat, turn, result}
        Deque<int[]> queue = new ArrayDeque<>();
        for (int c = 1; c < n; c++) {
            for (int t = 0; t < 2; t++) {
                color[0][c][t] = MOUSE; // mouse already at the hole
                queue.add(new int[]{0, c, t, MOUSE});
            }
        }
        for (int m = 1; m < n; m++) {
            for (int t = 0; t < 2; t++) {
                color[m][m][t] = CAT; // cat already caught the mouse
                queue.add(new int[]{m, m, t, CAT});
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int m = cur[0], c = cur[1], t = cur[2], result = cur[3];

            if (t == 1) {
                // This state has the cat to move, so it was reached by the
                // mouse moving; predecessors have turn 0 and mouse at pm.
                for (int pm : graph[m]) {
                    if (color[pm][c][0] != DRAW) continue;
                    if (result == MOUSE) {
                        color[pm][c][0] = MOUSE;
                        queue.add(new int[]{pm, c, 0, MOUSE});
                    } else if (--degree[pm][c][0] == 0) {
                        color[pm][c][0] = CAT;
                        queue.add(new int[]{pm, c, 0, CAT});
                    }
                }
            } else {
                // This state has the mouse to move, reached by the cat
                // moving; predecessors have turn 1 and cat at pc (pc != 0).
                for (int pc : graph[c]) {
                    if (pc == 0) continue;
                    if (color[m][pc][1] != DRAW) continue;
                    if (result == CAT) {
                        color[m][pc][1] = CAT;
                        queue.add(new int[]{m, pc, 1, CAT});
                    } else if (--degree[m][pc][1] == 0) {
                        color[m][pc][1] = MOUSE;
                        queue.add(new int[]{m, pc, 1, MOUSE});
                    }
                }
            }
        }

        return color[1][2][0]; // mouse starts at 1, cat at 2, mouse moves first
    }
}
