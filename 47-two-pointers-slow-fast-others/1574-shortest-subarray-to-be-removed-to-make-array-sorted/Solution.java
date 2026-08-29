/**
 * LeetCode 1574. Shortest Subarray to be Removed to Make Array Sorted
 * Approach: Two pointers find the longest non-decreasing prefix (ending
 * at `left`) and longest non-decreasing suffix (starting at `right`); if
 * the prefix already spans the whole array, it's sorted and the answer
 * is 0. Otherwise the removed subarray must sit between some prefix
 * index i and suffix index j with arr[i] <= arr[j] (so what remains
 * stays non-decreasing across the seam) -- found by converging two
 * pointers i (0..left) and j (right..n-1): whenever the seam is valid,
 * record the removal length and advance i (a smaller left removal
 * boundary can only help); otherwise advance j to find a bigger value.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int left = 0;
        while (left + 1 < n && arr[left] <= arr[left + 1]) left++;
        if (left == n - 1) return 0;

        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right]) right--;

        int result = Math.min(n - left - 1, right);
        int i = 0, j = right;
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                result = Math.min(result, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
