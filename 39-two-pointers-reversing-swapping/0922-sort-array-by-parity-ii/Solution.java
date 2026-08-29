/**
 * LeetCode 922. Sort Array By Parity II
 * Approach: Two pointers, each stepping by 2 instead of 1 -- `even`
 * walks the even indices looking for a stray odd value, `odd` walks the
 * odd indices looking for a stray even value. Whenever both are stuck on
 * a misplaced value, swapping them fixes both positions at once.
 * Time: O(n) | Space: O(1) extra
 */
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int even = 0, odd = 1;
        int n = nums.length;
        while (even < n && odd < n) {
            if (nums[even] % 2 == 0) {
                even += 2;
            } else if (nums[odd] % 2 == 1) {
                odd += 2;
            } else {
                int tmp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = tmp;
            }
        }
        return nums;
    }
}
