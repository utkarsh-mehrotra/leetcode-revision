import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1218. Longest Arithmetic Subsequence of Given Difference
 * Approach: Top-down memoized recursion by index -- length(i) is the
 * longest arithmetic subsequence (with the given common difference) ending
 * at index i, found via the most recent earlier index holding arr[i] -
 * difference. Recursing to i-1 first guarantees that lookup map is fully
 * populated for every index < i before length(i) is computed.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] arr;
    private int difference;
    private Integer[] memo;
    private Map<Integer, Integer> lastIndexOfValue;

    public int longestSubsequence(int[] arr, int difference) {
        this.arr = arr;
        this.difference = difference;
        this.memo = new Integer[arr.length];
        this.lastIndexOfValue = new HashMap<>();
        int best = 1;
        for (int i = 0; i < arr.length; i++) {
            best = Math.max(best, length(i));
        }
        return best;
    }

    private int length(int i) {
        if (memo[i] != null) return memo[i];
        if (i > 0) length(i - 1); // ensure the map holds every index < i first
        Integer prevIndex = lastIndexOfValue.get(arr[i] - difference);
        int result = (prevIndex != null) ? memo[prevIndex] + 1 : 1;
        memo[i] = result;
        lastIndexOfValue.put(arr[i], i);
        return result;
    }
}
