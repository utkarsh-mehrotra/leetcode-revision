/**
 * LeetCode 845. Longest Mountain in Array
 * Approach: Single caterpillar-style pass locating each peak (a strict
 * local max). From a peak, two pointers walk outward independently --
 * `left` back down the ascending run leading into it, `right` forward
 * down the descending run leaving it -- and the peak only counts as a
 * real mountain once both runs are non-empty, giving a total span of
 * right - left + 1. The outer index then jumps to `right` to avoid
 * re-scanning the descending run as a new ascending run.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int best = 0;
        int i = 1;
        while (i < n - 1) {
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                int left = i - 1;
                while (left > 0 && arr[left - 1] < arr[left]) left--;
                int right = i + 1;
                while (right < n - 1 && arr[right + 1] < arr[right]) right++;
                best = Math.max(best, right - left + 1);
                i = right + 1;
            } else {
                i++;
            }
        }
        return best;
    }
}
