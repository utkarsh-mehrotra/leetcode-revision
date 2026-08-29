/**
 * LeetCode 256. Paint House
 * Approach: Rolling DP over the 3 paint colors -- the cheapest way to paint
 * house i with color c is costs[i][c] plus the cheapest total for house
 * i-1 using either of the other two colors.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int minCost(int[][] costs) {
        if (costs.length == 0) return 0;
        int red = costs[0][0], blue = costs[0][1], green = costs[0][2];
        for (int i = 1; i < costs.length; i++) {
            int newRed = costs[i][0] + Math.min(blue, green);
            int newBlue = costs[i][1] + Math.min(red, green);
            int newGreen = costs[i][2] + Math.min(red, blue);
            red = newRed;
            blue = newBlue;
            green = newGreen;
        }
        return Math.min(red, Math.min(blue, green));
    }
}
