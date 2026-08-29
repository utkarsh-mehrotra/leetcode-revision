import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1687. Delivering Boxes from Storage to Ports
 * Approach: A trip covering boxes[i..k-1] costs 2 + (port switches strictly
 * within it) -- 1 for the mandatory first port visit, 1 for the return to
 * storage, plus 1 more per consecutive pair inside the trip that changes
 * port (a prefix-sum array over the whole sequence's port-change flags
 * gives that count in O(1), since transitions between two boxes both
 * inside the trip don't care where the trip's boundary is). This makes
 * dp[k] = min trips for boxes[0:k] = 2 + prefixSwitch[k] + min over valid
 * trip-start i of (dp[i] - prefixSwitch[i+1]). The valid range of i
 * (bounded by maxBoxes and maxWeight) only grows as k increases, a
 * classic sliding window, so a monotonic deque tracks that minimum in
 * O(1) amortized per step instead of memoizing arbitrary (i,k) pairs.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] prefixSwitch = new int[n + 1];
        long[] prefixWeight = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixWeight[i + 1] = prefixWeight[i] + boxes[i][1];
            int switchHere = (i > 0 && boxes[i][0] != boxes[i - 1][0]) ? 1 : 0;
            prefixSwitch[i + 1] = prefixSwitch[i] + switchHere;
        }

        int[] dp = new int[n + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // candidate trip-start indices, increasing (dp[i]-prefixSwitch[i+1])
        deque.add(0);
        int left = 0;

        for (int k = 1; k <= n; k++) {
            while (k - left > maxBoxes || prefixWeight[k] - prefixWeight[left] > maxWeight) {
                left++;
                while (!deque.isEmpty() && deque.peekFirst() < left) deque.pollFirst();
            }
            int front = deque.peekFirst();
            dp[k] = 2 + prefixSwitch[k] + (dp[front] - prefixSwitch[front + 1]);

            if (k < n) {
                int candVal = dp[k] - prefixSwitch[k + 1];
                while (!deque.isEmpty() && (dp[deque.peekLast()] - prefixSwitch[deque.peekLast() + 1]) >= candVal) {
                    deque.pollLast();
                }
                deque.addLast(k);
            }
        }
        return dp[n];
    }
}
