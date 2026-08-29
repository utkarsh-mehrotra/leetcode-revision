/**
 * LeetCode 777. Swap Adjacent in LR String
 * Approach: Two pointers walking `start` and `end` together, skipping
 * over 'X' at each. The remaining L/R characters must appear in the same
 * order and be the same letter at each matched position (swaps never
 * reorder L's and R's relative to each other, only shift them through
 * X's) -- and since 'L' can only slide LEFT and 'R' can only slide
 * RIGHT, each matched L's start-index must be >= its end-index, and each
 * matched R's must be <=.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n || j < n) {
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && end.charAt(j) == 'X') j++;

            if (i == n || j == n) return i == n && j == n;
            if (start.charAt(i) != end.charAt(j)) return false;
            if (start.charAt(i) == 'L' && i < j) return false;
            if (start.charAt(i) == 'R' && i > j) return false;
            i++;
            j++;
        }
        return true;
    }
}
