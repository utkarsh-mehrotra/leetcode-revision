/**
 * LeetCode 740. Delete and Earn
 * Approach: Bucket the total value earnable per number, which reduces the
 * problem to exactly House Robber over consecutive values (taking value v
 * forbids v-1 and v+1, mirroring "can't rob adjacent houses").
 * Time: O(n + maxVal) | Space: O(maxVal)
 */
class Solution {
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) maxVal = Math.max(maxVal, num);
        long[] earnings = new long[maxVal + 1];
        for (int num : nums) earnings[num] += num;

        long prev2 = 0, prev1 = 0;
        for (int v = 0; v <= maxVal; v++) {
            long curr = Math.max(prev1, prev2 + earnings[v]);
            prev2 = prev1;
            prev1 = curr;
        }
        return (int) prev1;
    }
}
