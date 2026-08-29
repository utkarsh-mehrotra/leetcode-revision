import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 898. Bitwise ORs of Subarrays
 * Approach: Top-down memoized recursion -- orsEndingAt(i) is the set of
 * all possible OR values for subarrays ending exactly at i, built from
 * every value in orsEndingAt(i-1) OR'd with nums[i], plus nums[i] alone.
 * That set can have at most ~30 distinct values (each element only ever
 * turns more bits on), keeping the whole computation near-linear.
 * Time: O(n * 30) | Space: O(n * 30)
 */
class Solution {
    private int[] nums;
    private Set<Integer>[] dp;

    @SuppressWarnings("unchecked")
    public int subarrayBitwiseORs(int[] nums) {
        this.nums = nums;
        this.dp = new Set[nums.length];
        Set<Integer> allResults = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            allResults.addAll(orsEndingAt(i));
        }
        return allResults.size();
    }

    private Set<Integer> orsEndingAt(int i) {
        if (dp[i] != null) return dp[i];
        Set<Integer> result = new HashSet<>();
        result.add(nums[i]);
        if (i > 0) {
            for (int prev : orsEndingAt(i - 1)) {
                result.add(prev | nums[i]);
            }
        }
        dp[i] = result;
        return result;
    }
}
