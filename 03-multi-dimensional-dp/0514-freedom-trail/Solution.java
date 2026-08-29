import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 514. Freedom Trail
 * Approach: Top-down memoized recursion over (ringIndex, keyIndex) -- to
 * spell key[keyIndex], try every ring position holding that character,
 * paying the shorter rotation distance (clockwise or counter-clockwise)
 * plus 1 for the button press, then recurse from that new ring position.
 * Time: O(ring.length * key.length^2) | Space: O(ring.length * key.length)
 */
class Solution {
    private String ring, key;
    private int ringLen;
    private Map<Character, List<Integer>> positions;
    private Integer[][] dp;

    public int findRotateSteps(String ring, String key) {
        this.ring = ring;
        this.key = key;
        this.ringLen = ring.length();
        this.positions = new HashMap<>();
        for (int i = 0; i < ringLen; i++) {
            positions.computeIfAbsent(ring.charAt(i), c -> new ArrayList<>()).add(i);
        }
        this.dp = new Integer[ringLen][key.length()];
        return solve(0, 0);
    }

    private int solve(int ringIndex, int keyIndex) {
        if (keyIndex == key.length()) return 0;
        if (dp[ringIndex][keyIndex] != null) return dp[ringIndex][keyIndex];
        int best = Integer.MAX_VALUE;
        for (int target : positions.get(key.charAt(keyIndex))) {
            int diff = Math.abs(ringIndex - target);
            int rotation = Math.min(diff, ringLen - diff);
            best = Math.min(best, rotation + 1 + solve(target, keyIndex + 1));
        }
        dp[ringIndex][keyIndex] = best;
        return best;
    }
}
