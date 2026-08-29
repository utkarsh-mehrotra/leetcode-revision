/**
 * LeetCode 1728. Cat and Mouse II
 * Approach: Minimax game-tree search with memoization over the state
 * (mouseRow, mouseCol, catRow, catCol, turnCount). Unlike 913's fixed
 * adjacency graph, moves here are grid jumps (each player may stay or
 * slide 1..jump cells in one of 4 directions, stopping at walls/edges),
 * so a forward memoized recursion is simpler than a reverse-BFS closure.
 * The turn count both breaks ties between the two players' moves and
 * caps the search: once it exceeds 2*rows*cols, every reachable state
 * must have repeated (pigeonhole on the bounded position space), so the
 * game is declared a draw from there -- this keeps the state space
 * finite despite the problem's literal 1000-move draw rule.
 * Time: O(rows^2 * cols^2 * turnCap * jump) | Space: O(rows^2 * cols^2 * turnCap)
 */
class Solution {
    private static final int DRAW = 0;
    private static final int MOUSE = 1;
    private static final int CAT = 2;
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private char[][] grid;
    private int rows, cols, catJump, mouseJump, turnCap;
    private Integer[][][][][] memo;

    public boolean canMouseWin(String[] gridArr, int catJump, int mouseJump) {
        rows = gridArr.length;
        cols = gridArr[0].length();
        grid = new char[rows][cols];
        int mouseR = 0, mouseC = 0, catR = 0, catC = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = gridArr[r].charAt(c);
                grid[r][c] = ch;
                if (ch == 'M') { mouseR = r; mouseC = c; }
                else if (ch == 'C') { catR = r; catC = c; }
            }
        }
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        this.turnCap = 2 * rows * cols;
        memo = new Integer[rows][cols][rows][cols][turnCap + 1];

        return dfs(mouseR, mouseC, catR, catC, 0) == MOUSE;
    }

    private int dfs(int mouseR, int mouseC, int catR, int catC, int turn) {
        if (turn >= turnCap) return DRAW;
        if (mouseR == catR && mouseC == catC) return CAT;
        if (grid[mouseR][mouseC] == 'F') return MOUSE;
        if (grid[catR][catC] == 'F') return CAT;
        if (memo[mouseR][mouseC][catR][catC][turn] != null) {
            return memo[mouseR][mouseC][catR][catC][turn];
        }

        boolean mouseTurn = turn % 2 == 0;
        int jump = mouseTurn ? mouseJump : catJump;
        int fromR = mouseTurn ? mouseR : catR;
        int fromC = mouseTurn ? mouseC : catC;

        // Default outcome if every move is exhausted without a winning one.
        int best = mouseTurn ? CAT : MOUSE;

        // "Stay in place" is always a legal move.
        best = evaluate(mouseTurn, mouseR, mouseC, catR, catC, turn, best);

        outer:
        for (int[] d : DIRS) {
            int r = fromR, c = fromC;
            for (int step = 1; step <= jump; step++) {
                r += d[0];
                c += d[1];
                if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '#') break;
                int nMouseR = mouseTurn ? r : mouseR;
                int nMouseC = mouseTurn ? c : mouseC;
                int nCatR = mouseTurn ? catR : r;
                int nCatC = mouseTurn ? catC : c;
                best = evaluate(mouseTurn, nMouseR, nMouseC, nCatR, nCatC, turn, best);
                if ((mouseTurn && best == MOUSE) || (!mouseTurn && best == CAT)) break outer;
            }
        }

        memo[mouseR][mouseC][catR][catC][turn] = best;
        return best;
    }

    // Recurses into one candidate move and folds its outcome into `best`,
    // preferring an immediate win for the mover, then a draw, then a loss.
    private int evaluate(boolean mouseTurn, int mouseR, int mouseC, int catR, int catC, int turn, int best) {
        int outcome = dfs(mouseR, mouseC, catR, catC, turn + 1);
        int winFor = mouseTurn ? MOUSE : CAT;
        if (outcome == winFor) return winFor;
        if (outcome == DRAW && best != winFor) return DRAW;
        return best;
    }
}
