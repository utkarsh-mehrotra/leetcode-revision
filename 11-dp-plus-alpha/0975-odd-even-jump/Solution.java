import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 975. Odd Even Jump
 * Approach: Precompute, for every index, the target of its odd jump
 * (smallest later value >= it -- via a TreeMap's ceilingKey while
 * scanning right to left) and even jump (largest later value <= it, via
 * floorKey). Top-down memoized recursion over (index, jumpIsOdd) then
 * checks whether that jump target can itself reach the end via the
 * OPPOSITE parity jump next.
 * Time: O(n log n) | Space: O(n)
 */
class Solution {
    private int[] oddTarget, evenTarget;
    private Boolean[][] dp; // dp[i][0]=even-jump-starts-here, dp[i][1]=odd-jump-starts-here
    private int n;

    public int oddEvenJumps(int[] arr) {
        n = arr.length;
        oddTarget = new int[n];
        evenTarget = new int[n];
        TreeMap<Integer, Integer> indexOfValue = new TreeMap<>();
        for (int i = n - 1; i >= 0; i--) {
            Map.Entry<Integer, Integer> ceil = indexOfValue.ceilingEntry(arr[i]);
            Map.Entry<Integer, Integer> floor = indexOfValue.floorEntry(arr[i]);
            oddTarget[i] = (ceil != null) ? ceil.getValue() : -1;
            evenTarget[i] = (floor != null) ? floor.getValue() : -1;
            indexOfValue.put(arr[i], i);
        }

        dp = new Boolean[n][2];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (canReachEnd(i, true)) count++;
        }
        return count;
    }

    private boolean canReachEnd(int i, boolean odd) {
        if (i == n - 1) return true;
        int key = odd ? 1 : 0;
        if (dp[i][key] != null) return dp[i][key];
        int target = odd ? oddTarget[i] : evenTarget[i];
        boolean result = target != -1 && canReachEnd(target, !odd);
        dp[i][key] = result;
        return result;
    }
}
