/**
 * LeetCode 763. Partition Labels
 * Approach: Precompute each character's last occurrence index, then scan
 * once tracking the current partition's end as the max last-occurrence
 * among characters seen so far in it. A fast pointer walks the string;
 * whenever it reaches the current partition's end, that partition is
 * closed (every one of its characters is guaranteed not to reappear
 * later), and a new one starts.
 * Time: O(n) | Space: O(1) (fixed 26-letter alphabet)
 */
class Solution {
    public java.util.List<Integer> partitionLabels(String s) {
        int[] lastOccurrence = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        java.util.List<Integer> result = new java.util.ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
