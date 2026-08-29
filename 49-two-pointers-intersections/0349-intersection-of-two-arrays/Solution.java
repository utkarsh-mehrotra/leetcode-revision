import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 349. Intersection of Two Arrays
 * Approach: Sort both arrays, then walk them with two pointers from the
 * start. Equal values are common elements (recorded in a set, which
 * absorbs any duplicate re-matches for free, so both pointers simply
 * advance by one); otherwise the pointer at the smaller value advances,
 * since that value can't appear later in the other (sorted) array
 * either.
 * Time: O(n log n + m log m) | Space: O(min(n,m)) output
 */
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> result = new HashSet<>();

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
        int idx = 0;
        for (int v : result) output[idx++] = v;
        return output;
    }
}
