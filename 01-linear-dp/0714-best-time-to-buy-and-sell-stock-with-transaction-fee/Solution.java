/**
 * LeetCode 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Approach: 2-state DP -- cash (no shares held) and hold (one share held),
 * charging the fee once per completed round trip on sale.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}
