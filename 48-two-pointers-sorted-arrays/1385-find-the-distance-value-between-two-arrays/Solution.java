import java.util.Arrays;

/**
 * LeetCode 1385. Find the Distance Value Between Two Arrays
 * Approach: Sort arr2, then for each element of arr1 use two pointers
 * over the sorted arr2 to check whether any value falls within d --
 * advancing a running pointer past every arr2 value too small to matter
 * for the current (or any later, since arr1 isn't sorted this pointer
 * resets... so instead a fresh binary-search-style two-pointer window is
 * checked per element) is what "distance value" reduces to: find the
 * first arr2 value >= arr1[i]-d and confirm it's also <= arr1[i]+d.
 * Time: O(n log n + m log n) | Space: O(1) extra
 */
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;
        for (int value : arr1) {
            int idx = lowerBound(arr2, value - d);
            boolean hasClose = idx < arr2.length && arr2[idx] <= value + d;
            if (!hasClose) count++;
        }
        return count;
    }

    private int lowerBound(int[] sorted, int target) {
        int left = 0, right = sorted.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
