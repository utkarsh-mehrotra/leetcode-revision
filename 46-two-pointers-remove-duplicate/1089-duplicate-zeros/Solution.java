/**
 * LeetCode 1089. Duplicate Zeros
 * Approach: Two passes, both pointer-driven. The forward pass finds how
 * far into the array the "kept" prefix extends once every zero counts
 * double (stopping early, with a special case, if a zero would land
 * exactly on the last valid slot and get cut off). The backward pass
 * then writes from that boundary out to the true end, walking a read
 * pointer backward through the kept prefix and a write pointer backward
 * through the full array -- writing back-to-front is what makes the
 * in-place duplication safe, since it never overwrites a value the read
 * pointer still needs.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int keptEnd = n - 1;
        int extraZeros = 0;

        for (int i = 0; i <= keptEnd; i++) {
            if (arr[i] == 0) {
                if (i == keptEnd) {
                    arr[keptEnd] = 0;
                    keptEnd--;
                    break;
                }
                extraZeros++;
            }
        }

        int write = keptEnd + extraZeros;
        for (int read = keptEnd; read >= 0; read--) {
            if (write < n) arr[write] = arr[read];
            write--;
            if (arr[read] == 0) {
                if (write < n) arr[write] = 0;
                write--;
            }
        }
    }
}
