/**
 * LeetCode 673. Number of Longest Increasing Subsequences
 * Approach: Top-down memoized recursion -- lengthAt(i)/countAt(i) is the
 * longest increasing subsequence ending at i and how many of that maximum
 * length exist. Combining a shorter predecessor's counts contributes to
 * countAt(i) only when it achieves the best length found so far.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[] lengthDp;
    private Integer[] countDp;

    public int findNumberOfLIS(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        this.lengthDp = new Integer[n];
        this.countDp = new Integer[n];
        int bestLength = 0;
        for (int i = 0; i < n; i++) {
            bestLength = Math.max(bestLength, lengthAt(i));
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (lengthAt(i) == bestLength) total += countAt(i);
        }
        return total;
    }

    private int lengthAt(int i) {
        ensureComputed(i);
        return lengthDp[i];
    }

    private int countAt(int i) {
        ensureComputed(i);
        return countDp[i];
    }

    private void ensureComputed(int i) {
        if (lengthDp[i] != null) return;
        int bestLen = 1;
        int count = 1;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                int candidateLen = lengthAt(j) + 1;
                if (candidateLen > bestLen) {
                    bestLen = candidateLen;
                    count = countAt(j);
                } else if (candidateLen == bestLen) {
                    count += countAt(j);
                }
            }
        }
        lengthDp[i] = bestLen;
        countDp[i] = count;
    }
}
