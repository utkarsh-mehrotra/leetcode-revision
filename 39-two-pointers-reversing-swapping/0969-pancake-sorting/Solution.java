import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 969. Pancake Sorting
 * Approach: Selection sort, but each "placement" is done with two flips
 * (each flip itself a two-pointer reverse of a prefix): for the largest
 * unplaced value, flip its prefix to bring it to the front, then flip
 * the whole unplaced region to send it to its correct final position at
 * the back of that region. Repeating for decreasing sizes places every
 * value in n-1 placements (at most 2 flips each).
 * Time: O(n²) | Space: O(n) output
 */
class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> flips = new ArrayList<>();
        int n = arr.length;
        for (int size = n; size > 1; size--) {
            int maxIdx = indexOfMax(arr, size);
            if (maxIdx == size - 1) continue;
            if (maxIdx != 0) {
                reverse(arr, 0, maxIdx);
                flips.add(maxIdx + 1);
            }
            reverse(arr, 0, size - 1);
            flips.add(size);
        }
        return flips;
    }

    private int indexOfMax(int[] arr, int size) {
        int maxIdx = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i] > arr[maxIdx]) maxIdx = i;
        }
        return maxIdx;
    }

    private void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }
}
