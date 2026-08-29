/**
 * LeetCode 75. Sort Colors
 * Approach: Dutch National Flag algorithm -- three pointers, with `low`
 * and `high` converging from both ends and `mid` scanning between them.
 * A 0 found at mid is swapped down to `low` (both advance, since the
 * value swapped into mid from low is already known to be 0 or 1 and safe
 * to re-examine... actually already-processed 0/1 region invariant means
 * it's safe to advance mid too); a 2 is swapped out to `high` (mid does
 * NOT advance, since the value swapped in from the unprocessed high end
 * still needs to be classified); a 1 is already in place and mid simply
 * advances.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            } else {
                mid++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
