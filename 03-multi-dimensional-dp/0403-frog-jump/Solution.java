import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 403. Frog Jump
 * Approach: Top-down memoized recursion over (stoneIndex, lastJumpSize) --
 * from the current stone, try jump sizes lastJump-1, lastJump, and
 * lastJump+1, succeeding if any of them lands on a stone from which the
 * last stone is reachable.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] stones;
    private Map<Integer, Integer> indexOf;
    private Boolean[][] dp; // dp[stoneIndex][jumpSize]

    public boolean canCross(int[] stones) {
        this.stones = stones;
        int n = stones.length;
        indexOf = new HashMap<>();
        for (int i = 0; i < n; i++) indexOf.put(stones[i], i);
        dp = new Boolean[n][n + 1];
        return canReachEnd(0, 0);
    }

    private boolean canReachEnd(int stoneIndex, int lastJump) {
        if (stoneIndex == stones.length - 1) return true;
        if (dp[stoneIndex][lastJump] != null) return dp[stoneIndex][lastJump];
        boolean result = false;
        for (int jump = lastJump - 1; jump <= lastJump + 1 && !result; jump++) {
            if (jump <= 0) continue;
            Integer nextIndex = indexOf.get(stones[stoneIndex] + jump);
            if (nextIndex != null) {
                result = canReachEnd(nextIndex, jump);
            }
        }
        dp[stoneIndex][lastJump] = result;
        return result;
    }
}
