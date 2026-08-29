/**
 * LeetCode 1147. Longest Chunked Palindrome Decomposition
 * Approach: Not a natural fit for memoized recursion -- the greedy choice
 * (always take the SHORTEST matching prefix/suffix pair available) is
 * provably optimal for maximizing piece count, so there's no benefit from
 * exploring/caching alternative splits. Two pointers close in from both
 * ends, growing a candidate chunk length until the prefix and suffix
 * chunks match, taking that pair greedily.
 * Time: O(n^2) | Space: O(1) extra
 */
class Solution {
    public int longestDecomposition(String text) {
        int l = 0, r = text.length() - 1;
        int count = 0;
        while (l <= r) {
            boolean matched = false;
            for (int len = 1; l + len - 1 < r - len + 1; len++) {
                if (text.regionMatches(l, text, r - len + 1, len)) {
                    count += 2;
                    l += len;
                    r -= len;
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                count++; // the remaining middle piece stands alone
                break;
            }
        }
        return count;
    }
}
