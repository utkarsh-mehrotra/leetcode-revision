import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1569. Number of Ways to Reorder Array to Get Same BST
 * Approach: The first element fixes the root; every insertion order that
 * preserves the relative order of the left-subtree values and the
 * right-subtree values (interleaved arbitrarily) builds the same BST.
 * ways(list) = C(n-1, leftSize) * ways(left) * ways(right), where C is
 * computed via a memoized Pascal's-triangle recursion.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private Long[][] chooseDp;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        chooseDp = new Long[n + 1][n + 1];
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);
        return (int) ((ways(list) - 1 + MOD) % MOD); // exclude the original ordering itself
    }

    private long ways(List<Integer> list) {
        if (list.size() <= 2) return 1;
        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < root) left.add(list.get(i));
            else right.add(list.get(i));
        }
        long combine = choose(list.size() - 1, left.size());
        return (combine * ways(left) % MOD) * ways(right) % MOD;
    }

    private long choose(int a, int b) {
        if (b == 0 || b == a) return 1;
        if (chooseDp[a][b] != null) return chooseDp[a][b];
        long result = (choose(a - 1, b - 1) + choose(a - 1, b)) % MOD;
        chooseDp[a][b] = result;
        return result;
    }
}
