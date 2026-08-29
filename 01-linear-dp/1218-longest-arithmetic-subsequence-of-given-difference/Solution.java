import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1218. Longest Arithmetic Subsequence of Given Difference
 * Approach: dp[v] = length of the longest arithmetic subsequence with
 * common difference `difference` ending in value v. Processing left to
 * right, dp[num] = dp[num - difference] + 1.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int best = 1;
        for (int num : arr) {
            int length = dp.getOrDefault(num - difference, 0) + 1;
            dp.put(num, length);
            best = Math.max(best, length);
        }
        return best;
    }
}
