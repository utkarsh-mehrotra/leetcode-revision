/**
 * LeetCode 899. Last Substring in Lexicographical Order
 * Approach: Two competing suffix-start pointers, `i` (best candidate so
 * far) and `j` (challenger), plus an offset `k` for how far their
 * characters have matched so far. Comparing s[i+k] to s[j+k]: equal
 * extends the match (k++); i winning means the challenger can never
 * catch up within the current match window, so j jumps past it
 * (j = max(j+1, i+k+1) via i = max(i+k+1, j), reset); j winning promotes
 * it to the new candidate. Every character is only ever advanced past
 * once across both pointers combined, giving linear time despite the
 * apparent nested comparison.
 * Time: O(n) | Space: O(1) extra
 */
class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0, j = 1, k = 0;
        while (j + k < n) {
            char a = s.charAt(i + k), b = s.charAt(j + k);
            if (a == b) {
                k++;
            } else if (a < b) {
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            } else {
                j = j + k + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }
}
