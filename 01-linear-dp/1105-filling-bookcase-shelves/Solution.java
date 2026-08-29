/**
 * LeetCode 1105. Filling Bookcase Shelves
 * Approach: DP where dp[i] = minimum height using the first i books. For
 * each i, try placing books[j..i) together on one new shelf (while the
 * cumulative width fits), taking the max height of that shelf.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            int width = 0, height = 0;
            for (int j = i; j >= 1; j--) {
                width += books[j - 1][0];
                if (width > shelfWidth) break;
                height = Math.max(height, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + height);
            }
        }
        return dp[n];
    }
}
