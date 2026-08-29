/**
 * LeetCode 1262. Greatest Sum Divisible by Three
 * Approach: Top-down memoized recursion over (i, r) -- dp(i, r) is the
 * best sum achievable from the first i elements whose remainder mod 3
 * equals r, built by skipping or taking nums[i-1].
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[][] memo;
    private static final int NEG_INF = Integer.MIN_VALUE / 2;

    public int maxSumDivThree(int[] nums) {
        this.nums = nums;
        this.memo = new Integer[nums.length + 1][3];
        return dp(nums.length, 0);
    }

    private int dp(int i, int r) {
        if (i == 0) return r == 0 ? 0 : NEG_INF;
        if (memo[i][r] != null) return memo[i][r];
        int skip = dp(i - 1, r);
        int prevRemainder = ((r - nums[i - 1]) % 3 + 3) % 3;
        int takeBase = dp(i - 1, prevRemainder);
        int take = (takeBase <= NEG_INF) ? NEG_INF : takeBase + nums[i - 1];
        int result = Math.max(skip, take);
        memo[i][r] = result;
        return result;
    }
}
