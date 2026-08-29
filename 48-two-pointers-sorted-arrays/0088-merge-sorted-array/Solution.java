/**
 * LeetCode 88. Merge Sorted Array
 * Approach: Two pointers starting from the END of each array's valid
 * region, writing into nums1 from its own back. Merging backward avoids
 * ever overwriting a nums1 element before it's been read, which a
 * forward merge into the same array couldn't guarantee.
 * Time: O(m+n) | Space: O(1)
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, write = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[write--] = nums1[i--];
            } else {
                nums1[write--] = nums2[j--];
            }
        }
    }
}
