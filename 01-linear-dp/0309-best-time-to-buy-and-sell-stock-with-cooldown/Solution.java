/**
 * LeetCode 309. Best Time to Buy and Sell Stock with Cooldown
 * Approach: 3-state machine per day -- hold (own a share), sold (just sold,
 * triggers cooldown next day), rest (no share, free to buy). Transition:
 * hold' = max(hold, rest - price); sold' = hold + price; rest' = max(rest, sold).
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int maxProfit(int[] prices) {
        int hold = Integer.MIN_VALUE, sold = 0, rest = 0;
        for (int price : prices) {
            int prevSold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prevSold);
        }
        return Math.max(sold, rest);
    }
}
