/**
 * LeetCode 1359. Count All Valid Pickup and Delivery Options
 * Approach: Closed-form recurrence. Inserting the i-th (pickup, delivery)
 * pair into a valid sequence built from the first i-1 pairs: the pickup can
 * go in any of (2i-1) open slots among the existing 2(i-1) events, and its
 * delivery must then go in one of the remaining i slots after it -- giving
 * i * (2i-1) ways to extend, multiplied across i = 1..n.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int countOrders(int n) {
        long ways = 1;
        for (int i = 1; i <= n; i++) {
            ways = (ways * i % MOD) * (2 * i - 1) % MOD;
        }
        return (int) ways;
    }
}
