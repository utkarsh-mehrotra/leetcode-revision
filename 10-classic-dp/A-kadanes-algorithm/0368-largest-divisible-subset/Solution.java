import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 368. Largest Divisible Subset
 * Approach: Sort ascending, then top-down memoized recursion (LIS-style)
 * -- sizeEndingAt(i) is the largest divisible chain ending at nums[i],
 * built from the best sizeEndingAt(j) over every earlier j that divides
 * nums[i]. Predecessors are tracked alongside for reconstruction.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] nums;
    private Integer[] dp;
    private int[] predecessor;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        int n = nums.length;
        this.dp = new Integer[n];
        this.predecessor = new int[n];
        Arrays.fill(predecessor, -1);

        int bestIndex = 0;
        for (int i = 0; i < n; i++) {
            if (sizeEndingAt(i) > sizeEndingAt(bestIndex)) bestIndex = i;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = bestIndex; i != -1; i = predecessor[i]) {
            result.add(nums[i]);
        }
        return result;
    }

    private int sizeEndingAt(int i) {
        if (dp[i] != null) return dp[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (nums[i] % nums[j] == 0 && sizeEndingAt(j) + 1 > best) {
                best = sizeEndingAt(j) + 1;
                predecessor[i] = j;
            }
        }
        dp[i] = best;
        return best;
    }
}
