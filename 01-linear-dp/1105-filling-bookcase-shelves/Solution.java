/**
 * LeetCode 1105. Filling Bookcase Shelves
 * Approach: Top-down memoized recursion -- best(i) is the minimum height
 * for books[i:], trying every shelf that starts at i and extends while it
 * still fits within shelfWidth.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[][] books;
    private int shelfWidth;
    private Integer[] dp;

    public int minHeightShelves(int[][] books, int shelfWidth) {
        this.books = books;
        this.shelfWidth = shelfWidth;
        this.dp = new Integer[books.length + 1];
        return best(0);
    }

    private int best(int i) {
        int n = books.length;
        if (i == n) return 0;
        if (dp[i] != null) return dp[i];
        int result = Integer.MAX_VALUE;
        int width = 0, height = 0;
        for (int j = i; j < n; j++) {
            width += books[j][0];
            if (width > shelfWidth) break;
            height = Math.max(height, books[j][1]);
            result = Math.min(result, height + best(j + 1));
        }
        dp[i] = result;
        return result;
    }
}
