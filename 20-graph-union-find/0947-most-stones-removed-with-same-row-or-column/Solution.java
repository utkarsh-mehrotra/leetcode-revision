import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 947. Most Stones Removed with Same Row or Column
 * Approach: Union-Find over rows and columns as a single shared index
 * space (columns bitwise-complemented so they can never collide with row
 * IDs) -- union each stone's row with its column. Within each connected
 * component, every stone but one can be chain-removed via a shared row
 * or column, so the answer is stones minus the number of distinct components.
 * Time: O(stones * alpha(stones)) | Space: O(stones)
 */
class Solution {
    private Map<Integer, Integer> parent = new HashMap<>();
    private int components = 0;

    public int removeStones(int[][] stones) {
        for (int[] stone : stones) {
            union(stone[0], ~stone[1]);
        }
        return stones.length - components;
    }

    private int find(int x) {
        if (!parent.containsKey(x)) {
            parent.put(x, x);
            components++;
        }
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        parent.put(ra, rb);
        components--;
    }
}
