/**
 * LeetCode 27. Remove Element
 * Approach: Two pointers converging from both ends. `left` scans forward
 * looking for a value equal to `val`; when found, it's swapped with the
 * current tail (`right`), which shrinks the "unprocessed" region by one
 * from the back -- this avoids shifting every later element the way a
 * simple overwrite-and-advance scan would, at the cost of not preserving
 * order (which the problem allows).
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int removeElement(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
