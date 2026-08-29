import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1553. Minimum Number of Days to Eat N Oranges
 * Approach: Top-down memoized recursion -- rather than eating one by one
 * down from n, it's always at least as good to eat down to the nearest
 * multiple of 2 or 3 one-by-one, then halve/third in a single day: days(n)
 * = min(n%2 + 1 + days(n/2), n%3 + 1 + days(n/3)). n can reach 2*10^9, so
 * the memo is a hash map keyed by the (sparse) values actually visited.
 * Time: O(log^2 n) | Space: O(log^2 n)
 */
class Solution {
    private Map<Integer, Integer> dp;

    public int minDays(int n) {
        dp = new HashMap<>();
        return days(n);
    }

    private int days(int n) {
        if (n <= 1) return n;
        Integer cached = dp.get(n);
        if (cached != null) return cached;
        int viaTwo = (n % 2) + 1 + days(n / 2);
        int viaThree = (n % 3) + 1 + days(n / 3);
        int result = Math.min(viaTwo, viaThree);
        dp.put(n, result);
        return result;
    }
}
