import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 956. Tallest Billboard
 * Approach: Top-down memoized recursion over (index, diff), where diff is
 * (tallerSide - shorterSide) using rods decided so far. best(i, diff) is
 * the max achievable shorterSide from rods[i:] that eventually balances
 * this diff back to zero; each rod is skipped, added to the taller side,
 * or added to the shorter side.
 * Time: O(n * totalHeight) | Space: O(n * totalHeight)
 */
class Solution {
    private int[] rods;
    private List<Map<Integer, Integer>> dp;
    private static final int NEG_INF = Integer.MIN_VALUE / 2;

    public int tallestBillboard(int[] rods) {
        this.rods = rods;
        dp = new ArrayList<>();
        for (int i = 0; i <= rods.length; i++) dp.add(new HashMap<>());
        return best(0, 0);
    }

    private int best(int i, int diff) {
        if (i == rods.length) return diff == 0 ? 0 : NEG_INF;
        Map<Integer, Integer> level = dp.get(i);
        Integer cached = level.get(diff);
        if (cached != null) return cached;

        int skip = best(i + 1, diff);
        int addToTaller = best(i + 1, diff + rods[i]);
        int addToShorter = rods[i] + best(i + 1, diff - rods[i]);
        int result = Math.max(skip, Math.max(addToTaller, addToShorter));

        level.put(diff, result);
        return result;
    }
}
