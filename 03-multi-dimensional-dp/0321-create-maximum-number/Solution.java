/**
 * LeetCode 321. Create Maximum Number
 * Approach: Not a natural fit for memoized recursion -- the standard
 * solution is greedy + merge. For each way to split k digits between the
 * two arrays, pick the lexicographically largest length-i subsequence
 * from nums1 (via a monotonic stack) and length-(k-i) subsequence from
 * nums2, then greedily merge the two subsequences (always taking from
 * whichever remaining suffix is lexicographically larger) to form the
 * largest possible combined sequence. The best candidate across all
 * splits is the answer.
 * Time: O(k * (n1 + n2 + k^2)) | Space: O(n1 + n2 + k)
 */
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] best = new int[k];
        for (int i = Math.max(0, k - n2); i <= Math.min(k, n1); i++) {
            int[] candidate = merge(maxSubsequence(nums1, i), maxSubsequence(nums2, k - i));
            if (greater(candidate, 0, best, 0)) {
                best = candidate;
            }
        }
        return best;
    }

    // Largest subsequence of `nums` with exactly `len` digits, preserving relative order.
    private int[] maxSubsequence(int[] nums, int len) {
        int[] stack = new int[len];
        int top = -1;
        int toDrop = nums.length - len;
        for (int num : nums) {
            while (top >= 0 && stack[top] < num && toDrop > 0) {
                top--;
                toDrop--;
            }
            if (top + 1 < len) {
                stack[++top] = num;
            } else {
                toDrop--;
            }
        }
        return stack;
    }

    // Greedily interleave a and b, always taking the next digit from whichever
    // remaining suffix is lexicographically larger.
    private int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, r = 0;
        while (i < a.length || j < b.length) {
            result[r++] = greater(a, i, b, j) ? a[i++] : b[j++];
        }
        return result;
    }

    // Whether a[i:] is lexicographically greater than b[j:].
    private boolean greater(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length && a[i] == b[j]) {
            i++;
            j++;
        }
        if (j == b.length) return true;
        if (i == a.length) return false;
        return a[i] > b[j];
    }
}
