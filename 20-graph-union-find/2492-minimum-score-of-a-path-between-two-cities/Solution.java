/**
 * LeetCode 2492. Minimum Score of a Path Between Two Cities
 * Approach: A path between cities 1 and n may reuse roads/cities freely,
 * so the reachable set from either endpoint is exactly its Union-Find
 * component, and the answer is the minimum edge weight anywhere within
 * that component (every edge in it is usable by some walk, including
 * doubling back).
 * Time: O((n+roads) * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent;

    public int minScore(int n, int[][] roads) {
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;
        for (int[] road : roads) {
            union(road[0], road[1]);
        }

        int target = find(1);
        int best = Integer.MAX_VALUE;
        for (int[] road : roads) {
            if (find(road[0]) == target) {
                best = Math.min(best, road[2]);
            }
        }
        return best;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra != rb) parent[ra] = rb;
    }
}
