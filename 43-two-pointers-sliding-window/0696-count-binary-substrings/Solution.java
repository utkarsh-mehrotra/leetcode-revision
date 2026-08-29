/**
 * LeetCode 696. Count Binary Substrings
 * Approach: Caterpillar run-length scan. Every valid substring is formed
 * by taking equal-length runs of 0s and 1s adjacent to each other, so
 * tracking just the previous run's length and the current run's length
 * (no need to store the actual runs) is enough: whenever the run
 * changes, min(prevRunLength, currentRunLength) new valid substrings
 * become available, anchored at the boundary between the two runs.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int countBinarySubstrings(String s) {
        int prevRunLength = 0, currentRunLength = 1, count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currentRunLength++;
            } else {
                count += Math.min(prevRunLength, currentRunLength);
                prevRunLength = currentRunLength;
                currentRunLength = 1;
            }
        }
        count += Math.min(prevRunLength, currentRunLength);
        return count;
    }
}
