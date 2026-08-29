/**
 * LeetCode 658. Find K Closest Elements
 * Approach: Two pointers converging from both ends of the FULL array.
 * While the window [left, right] holds more than k elements, discard
 * whichever end is currently farther from x -- the element closer to x
 * (with ties broken toward the smaller value, i.e. keep the left one)
 * can never be excluded from an optimal answer while the far one is
 * still included, so this greedy shrink is safe.
 * Time: O(n) | Space: O(k) output
 */
class Solution {
    public java.util.List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1;
        while (right - left + 1 > k) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
        }

        java.util.List<Integer> result = new java.util.ArrayList<>();
        for (int i = left; i <= right; i++) result.add(arr[i]);
        return result;
    }
}
