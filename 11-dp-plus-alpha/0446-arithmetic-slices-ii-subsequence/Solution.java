import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 446. Arithmetic Slices II - Subsequence
 * Approach: Top-down memoized recursion by index -- solve(i) fills a
 * per-index map from common-difference to the count of length->=2
 * arithmetic subsequences ending at i with that difference. Extending a
 * length->=2 run at some earlier j into i creates a NEW length->=3 slice
 * for each of those, so it's added straight to the answer, while the
 * combined count (existing + the fresh 2-element pair) is stored for
 * future extensions. Recursing to i-1 first guarantees every earlier
 * index's map is ready before solve(i) reads it.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] nums;
    private int n;
    private Map<Long, Integer>[] dp;
    private Boolean[] computed;
    private long answer;

    @SuppressWarnings("unchecked")
    public int numberOfArithmeticSlices(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.dp = new HashMap[n];
        this.computed = new Boolean[n];
        this.answer = 0;
        for (int i = 0; i < n; i++) solve(i);
        return (int) answer;
    }

    private void solve(int i) {
        if (Boolean.TRUE.equals(computed[i])) return;
        if (i > 0) solve(i - 1);
        dp[i] = new HashMap<>();
        for (int j = 0; j < i; j++) {
            long diff = (long) nums[i] - nums[j];
            int countAtJ = dp[j].getOrDefault(diff, 0);
            answer += countAtJ; // each existing run at j extends to a length>=3 slice ending at i
            dp[i].merge(diff, countAtJ + 1, Integer::sum); // +1 for the fresh pair (j, i)
        }
        computed[i] = true;
    }
}
