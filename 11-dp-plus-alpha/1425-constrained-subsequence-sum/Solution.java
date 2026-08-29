import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1425. Constrained Subsequence Sum
 * Approach: Top-down memoized recursion -- best(i) is the max sum of a
 * subsequence ending at i where consecutive chosen indices are within k
 * of each other, so it's nums[i] plus the best non-negative best(j) over
 * the last k positions. A monotonic decreasing deque of recent best(j)
 * values (built in the same left-to-right order the recursion naturally
 * proceeds, since best(i) always resolves best(i-1) first) gives that
 * window max in O(1) instead of an O(k) scan.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private int k;
    private Integer[] dp;
    private Deque<Integer> window; // indices with decreasing best(j), front = window max

    public int constrainedSubsetSum(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        this.dp = new Integer[nums.length];
        this.window = new ArrayDeque<>();
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            answer = Math.max(answer, best(i));
        }
        return answer;
    }

    private int best(int i) {
        if (dp[i] != null) return dp[i];
        while (!window.isEmpty() && window.peekFirst() < i - k) window.pollFirst();
        int windowMax = window.isEmpty() ? 0 : Math.max(0, dp[window.peekFirst()]);
        int result = nums[i] + windowMax;
        dp[i] = result;
        while (!window.isEmpty() && dp[window.peekLast()] <= result) window.pollLast();
        window.addLast(i);
        return result;
    }
}
