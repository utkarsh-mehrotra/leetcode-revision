import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 464. Can I Win
 * Approach: Top-down memoized recursion over `usedMask` (which numbers
 * 1..maxChoosableInteger have been picked) -- the current player wins if
 * some unused number either reaches desiredTotal immediately, or leaves
 * the opponent facing a losing usedMask. The running total is implicit:
 * it's derivable from usedMask, so it isn't part of the memo key.
 * Time: O(2^n * n) | Space: O(2^n)
 */
class Solution {
    private int maxChoosable;
    private int desiredTotal;
    private Map<Integer, Boolean> dp;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        this.maxChoosable = maxChoosableInteger;
        this.desiredTotal = desiredTotal;
        int maxPossibleSum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (maxPossibleSum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;
        this.dp = new HashMap<>();
        return win(0, desiredTotal);
    }

    private boolean win(int usedMask, int remainingTotal) {
        if (dp.containsKey(usedMask)) return dp.get(usedMask);
        boolean result = false;
        for (int i = 1; i <= maxChoosable; i++) {
            int bit = 1 << i;
            if ((usedMask & bit) != 0) continue;
            if (i >= remainingTotal || !win(usedMask | bit, remainingTotal - i)) {
                result = true;
                break;
            }
        }
        dp.put(usedMask, result);
        return result;
    }
}
