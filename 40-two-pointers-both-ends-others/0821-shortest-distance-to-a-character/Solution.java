/**
 * LeetCode 821. Shortest Distance to a Character
 * Approach: Two sweeps starting from opposite ends, mirroring the
 * "running from both ends" pattern one direction at a time: a left-to-
 * right pass records each index's distance to the nearest `c` seen SO
 * FAR, and a right-to-left pass takes the min against the nearest `c`
 * coming from the other direction. Together they give the true nearest
 * occurrence from either side.
 * Time: O(n) | Space: O(n) output
 */
class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] result = new int[n];
        int lastSeen = Integer.MIN_VALUE / 2;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) lastSeen = i;
            result[i] = i - lastSeen;
        }
        lastSeen = Integer.MAX_VALUE / 2;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) lastSeen = i;
            result[i] = Math.min(result[i], lastSeen - i);
        }
        return result;
    }
}
