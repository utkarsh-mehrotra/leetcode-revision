import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1023. Camelcase Matching
 * Approach: For each query, a two-pointer scan against the pattern.
 * Walking the query left to right: a character equal to the pattern's
 * current character advances BOTH pointers (it's an intentional match);
 * a lowercase character not matching the pattern is allowed to be
 * silently "inserted" (only the query pointer advances); but an
 * uppercase character that doesn't match the pattern immediately fails,
 * since only lowercase insertions are legal. The query matches iff the
 * pattern pointer also reaches its end.
 * Time: O(sum of query lengths) | Space: O(1) extra per query
 */
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String query : queries) {
            result.add(matches(query, pattern));
        }
        return result;
    }

    private boolean matches(String query, String pattern) {
        int i = 0, j = 0;
        while (i < query.length()) {
            char c = query.charAt(i);
            if (j < pattern.length() && c == pattern.charAt(j)) {
                i++;
                j++;
            } else if (Character.isLowerCase(c)) {
                i++;
            } else {
                return false;
            }
        }
        return j == pattern.length();
    }
}
