/**
 * LeetCode 990. Satisfiability of Equality Equations
 * Approach: Union-Find -- first union every pair declared equal ("=="),
 * then check every "!=" pair: the equations are satisfiable iff no
 * "!=" pair ended up in the same component.
 * Time: O(n * alpha(26)) | Space: O(26)
 */
class Solution {
    private int[] parent = new int[26];

    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < 26; i++) parent[i] = i;
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                union(eq.charAt(0) - 'a', eq.charAt(3) - 'a');
            }
        }
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                if (find(eq.charAt(0) - 'a') == find(eq.charAt(3) - 'a')) return false;
            }
        }
        return true;
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
