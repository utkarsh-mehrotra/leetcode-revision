import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 350. Intersection of Two Arrays II
 * Approach: Sort both arrays, then walk them with two pointers from the
 * start. Unlike 349, every matching occurrence counts (not just distinct
 * values), so an equal pair is emitted once per pointer position and
 * BOTH pointers advance by exactly one -- naturally producing the
 * correct multiplicity (min of each value's count in the two arrays).
 * Time: O(n log n + m log m) | Space: O(min(n,m)) output
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] output = new int[result.size()];
        for (int k = 0; k < output.length; k++) output[k] = result.get(k);
        return output;
    }
}
